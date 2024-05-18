package main.java.cli.commands.files;
import main.java.cli.DefaultCommand;
import main.java.exeptions.InvalidArguments;

import java.util.List;

public class SaveAs extends DefaultCommand {

    @Override
    public void execute(List<String> arguments) {
        try {
            if (arguments.size() != 1) {
                throw new InvalidArguments("Usage: saveas <filepath>");
            }

            String filePath = arguments.get(0);
            AutomatonManager.getInstance().saveAs(filePath);
        } catch (InvalidArguments e) {
            System.err.println(e.getMessage());
        }
    }
}

