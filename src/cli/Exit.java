package cli;

import java.util.List;

public class Exit extends DefaultCommand {
    public Exit() {
    }

    @Override
    public void execute(List<String> arguments) {
        System.exit(1);
    }
}