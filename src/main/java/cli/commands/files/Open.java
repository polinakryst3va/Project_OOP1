package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.files.AlreadyOpenedFileException;
import main.java.exeptions.files.ErrorOpeningException;
import main.java.exeptions.comands.InvalidArguments;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Open extends DefaultCommand {

    public Open() {
    }

    @Override
    public void execute(List<String> arguments) throws ErrorOpeningException {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() != null) {
                throw new AlreadyOpenedFileException("A file is already opened!");
            }
            if (arguments.size() != 1) {
                throw new InvalidArguments("Invalid number of arguments. Usage: open <file>");
            }
            String filePath = arguments.get(0);
            FileOpener fileOpener = new FileOpener(filePath);
            if (fileOpener.openFile()) {
                AutomatonManager.getInstance().setOpenedFile(new File(filePath));
            }
        } catch (AlreadyOpenedFileException | InvalidArguments e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occurred while opening the file: " + e.getMessage());
        }
    }
}