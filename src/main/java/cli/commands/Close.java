package main.java.cli.commands;
import main.java.cli.DefaultCommand;
import java.util.List;

public class Close extends DefaultCommand {

    public Close() {
    }

    @Override
    public void execute(List<String> arguments) {
        AutomatonManager.getInstance().close();
    }
}
