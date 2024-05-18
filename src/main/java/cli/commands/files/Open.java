package main.java.cli.commands.files;
import main.java.cli.DefaultCommand;
import main.java.exeptions.AlreadyOpenedFIleException;
import main.java.exeptions.InvalidArguments;
import main.java.exeptions.NoFileOpenException;

import java.io.File;
import java.util.List;

public class Open extends DefaultCommand {

    public Open() {
    }


    @Override
    public void execute(List<String> arguments) throws NoFileOpenException {
        try {
            if(AutomatonManager.getInstance().getOpenedFile() != null) {
                throw new AlreadyOpenedFIleException("A file is already opened!");
            }
            if (arguments.size() != 1) {
                throw new InvalidArguments("Invalid number of arguments. Usage: open <file>");
            }
            String filePath = arguments.get(0);
            FileOpener fileOpener = new FileOpener(filePath);
            if (fileOpener.openFile()) {
                AutomatonManager.getInstance().setOpenedFile(new File(filePath));
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());

        }
    }
}