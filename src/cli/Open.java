package cli;

import interfaces.FileOpener;

import java.util.List;

public class Open extends DefaultCommand {
    public Open() {
    }

    @Override
    public void execute(List<String> arguments) {
        FileOpener fileOpener = new FileOpener(arguments.get(0));
        fileOpener.openFile();
    }
}
