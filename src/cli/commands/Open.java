package cli.commands;

import cli.DefaultCommand;

import java.io.File;
import java.util.List;

public class Open extends DefaultCommand {

    public Open() {
    }

    @Override
    public void execute(List<String> arguments) {
        String filename = arguments.isEmpty() ? "" : arguments.get(0);
        File fileToOpen = new File(filename);
        AutomatonManager manager = AutomatonManager.getInstance();
        manager.open(fileToOpen);
    }
}
