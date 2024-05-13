package main.java.cli.commands;

import main.java.cli.DefaultCommand;
import java.util.List;

public class SaveAs extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.err.println("Usage: saveas <filepath>");
            return;
        }

        String filePath = arguments.get(0);
        AutomatonManager.getInstance().saveAs(filePath);


    }
}
