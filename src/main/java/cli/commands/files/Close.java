package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;

public class Close extends DefaultCommand {

    public Close() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            AutomatonManager.getInstance().close();
        } catch (NoOpenFileException e) {
            System.err.println(e.getMessage());
        }
    }
}
