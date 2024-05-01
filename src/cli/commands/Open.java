package cli.commands;

import cli.DefaultCommand;
import interfaces.FileOpener;

import java.util.List;

public class Open extends DefaultCommand {

    public Open() {
    }

    @Override
    public void execute(List<String> arguments) {
        String filename = arguments.isEmpty() ? "" : arguments.get(0);
        FileOpener fileOpener = new FileOpener(filename);
        fileOpener.openFile();
    }
}
