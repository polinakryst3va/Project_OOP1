package main.java.cli.commands;
import main.java.cli.DefaultCommand;

import java.util.List;

public class Exit extends DefaultCommand {

    public Exit() {
    }

    @Override
    public void execute(List<String> arguments) {
        System.out.println("Exiting...");
        System.exit(1);
    }
}
