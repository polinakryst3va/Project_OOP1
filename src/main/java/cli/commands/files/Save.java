package main.java.cli.commands.files;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import main.java.realization.models.AutomatonList;

import java.util.List;


public class Save extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.isEmpty()) {
                AutomatonManager.getInstance().save();
            } else if (arguments.size() >= 2) {
                int id = Integer.parseInt(arguments.get(0));
                String filename = arguments.get(1);

                if (AutomatonList.getInstance().getAutomatons().containsKey(id)) {
                    AutomatonManager.getInstance().save(id, filename);
                    System.out.println("Successfully saved '" + filename + "'.");
                } else {
                    throw new AutomatonNotFoundException("Automaton with ID " + id + " not found.");
                }
            } else {
                System.err.println("Error: 'save' command requires an ID and a filename.");
            }
        } catch (NoOpenFileException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}