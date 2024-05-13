package main.java.cli.commands;

import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;

import java.util.List;

public class Save extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        if(arguments.isEmpty()) {
            AutomatonManager.getInstance().save();
        }
        else {
            AutomatonManager.getInstance().save(Integer.parseInt(arguments.get(0)), arguments.get(1));
        }

    }
}
