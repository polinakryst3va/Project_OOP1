package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.comands.InvalidArguments;
import main.java.exeptions.files.NoOpenFileException;

import java.util.List;

public class SaveAs extends DefaultCommand {

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new InvalidArguments("Usage: saveas <filepath>");
            }

            String filePath = arguments.get(0);
            AutomatonManager.getInstance().saveAs(filePath);
        } catch (NoOpenFileException | InvalidArguments e) {
            System.err.println(e.getMessage());
        }
    }
}
