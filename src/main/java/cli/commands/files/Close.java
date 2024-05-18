package main.java.cli.commands.files;
import main.java.cli.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;

import java.util.List;

public class Close extends DefaultCommand {

    public Close() {
    }

    @Override
    public void execute(List<String> arguments) {
        AutomatonManager.getInstance().close();
    }
}
