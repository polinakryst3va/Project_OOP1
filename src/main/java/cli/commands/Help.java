package main.java.cli.commands;
import main.java.cli.DefaultCommand;
import main.java.cli.Operations;
import java.util.List;

public class Help extends DefaultCommand {

    public Help() {
    }

    @Override
    public void execute(List<String> arguments) {
        for (Operations value : Operations.values()) {

            System.out.printf("%-20s%s\n",value.getCommand().toUpperCase(), value.getDescription());

        }

    }
}
