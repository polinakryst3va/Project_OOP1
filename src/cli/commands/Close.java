package cli.commands;
import cli.DefaultCommand;
import java.util.List;

public class Close extends DefaultCommand {

    public Close() {
    }

    @Override
    public void execute(List<String> arguments) {
        AutomatonManager manager = AutomatonManager.getInstance();
        manager.close();
    }
}
