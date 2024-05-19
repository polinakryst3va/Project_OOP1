package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.comands.InvalidArguments;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;

public class Close extends DefaultCommand {

    public Close() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {

            if (!arguments.isEmpty()) {
                throw new InvalidArguments("Usage: close");
            }

            AutomatonManager.getInstance().close();
        } catch (InvalidArguments e) {
            System.err.println(e.getMessage());
        } catch (NoOpenFileException e) {
            System.err.println(e.getMessage());
        }
    }
}
