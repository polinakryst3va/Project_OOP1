package main.java.cli.commands;

import main.java.cli.DefaultCommand;

import java.io.File;
import java.util.List;

public class Open extends DefaultCommand {

    public Open() {
    }

    @Override
    public void execute(List<String> arguments) {
        if(AutomatonManager.getInstance().getOpenedFile() != null) {
            System.err.println("file already opened");
            return;
        }
        String filePath = arguments.get(0);
        FileOpener fileOpener = new FileOpener(filePath);
        if (fileOpener.openFile()) {
            AutomatonManager.getInstance().setOpenedFile(new File(filePath));
        }
    }
}
