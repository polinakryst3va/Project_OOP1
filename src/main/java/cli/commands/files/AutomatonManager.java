package main.java.cli.commands.files;
import main.java.anotherpackage.AutomatonList;
import main.java.exeptions.NoFileOpenException;

import java.io.*;
import java.util.HashMap;

public class AutomatonManager {
    private static AutomatonManager instance = null;
    private File openedFile = null;

    private AutomatonManager() {
    }

    public static AutomatonManager getInstance() {
        if (instance == null) {
            instance = new AutomatonManager();
        }
        return instance;
    }

    public File getOpenedFile() {
        return openedFile;
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }



    public void save() {
        FileAutomatonWriter writer = new FileAutomatonWriter(openedFile.getPath(), FileOpener.getContent());
        writer.write();

    }

    public void save(int id, String file) {
        FileAutomatonWriter writer = new FileAutomatonWriter(file, FileOpener.getContent());
        writer.append(id);
    }

    public void saveAs(String file) {
        FileAutomatonWriter writer = new FileAutomatonWriter(file, FileOpener.getContent());
        writer.write();
    }


    public void close() throws NoFileOpenException {
        try {
            if (openedFile != null) {
                System.out.println("Successfully closed " + openedFile.getName());
                this.openedFile = null;
                AutomatonList.getInstance().setAutomatons(new HashMap<>());
            } else {
                throw new NoFileOpenException("Error: No file is currently open.");
            }
        } catch (NoFileOpenException e) {
            System.err.println(e.getMessage());
        }
    }

}
