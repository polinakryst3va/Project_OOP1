package cli.commands;

import cli.DefaultCommand;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Save extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        try {
            AutomatonManager.getInstance().save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
