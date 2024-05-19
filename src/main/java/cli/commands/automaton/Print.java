package main.java.cli.commands.automaton;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;

public class Print extends DefaultCommand {

    public Print() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Usage: print <id>");
            }

            int id;
            try {
                id = Integer.parseInt(arguments.get(0));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid automaton ID. Please provide a valid integer ID.");
            }
            Automaton automaton = getAutomatonById(id);

            if (automaton == null) {
                throw new AutomatonNotFoundException("Automaton with id " + id + " not found.");
            }

            System.out.println(automaton.toString());
        } catch (NoOpenFileException | IllegalArgumentException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private Automaton getAutomatonById(int id) {
        AutomatonList automatonList = AutomatonList.getInstance();
        return automatonList.getAutomatons().get(id);
    }
}