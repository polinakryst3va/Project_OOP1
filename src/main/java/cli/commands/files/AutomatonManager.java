package main.java.cli.commands.files;
import main.java.realization.AutomatonList;
import main.java.exeptions.files.NoOpenFileException;
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
        if (openedFile == null) {
            System.err.println("Error: No file is currently open.");
            return;
        }
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

    public void close() throws NoOpenFileException {
        if (openedFile != null) {
            System.out.println("Successfully closed " + openedFile.getName());
            this.openedFile = null;
            AutomatonList.getInstance().setAutomatons(new HashMap<>());
        } else {
            throw new NoOpenFileException("Error: No file is currently open.");
        }
    }
}