package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.files.NoOpenFileException;

import java.util.List;

public class SaveIdToFile extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (arguments.size() < 2) {
                throw new IllegalArgumentException("Error: Insufficient arguments. Usage: save automaton <id> <filename>");
            }
            AutomatonManager.getInstance().save(Integer.parseInt(arguments.get(0)), arguments.get(1));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (NoOpenFileException e) {
            System.err.println("Error: No file is currently open.");
        }
    }
}
