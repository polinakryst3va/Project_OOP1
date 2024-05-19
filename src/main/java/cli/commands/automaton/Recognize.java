package main.java.cli.commands.automaton;
import main.java.realization.Automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;

public class Recognize extends DefaultCommand {

    public Recognize() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 2) {
                throw new IllegalArgumentException("Usage: recognize <id> <word>");
            }

            int id = parseId(arguments.get(0));
            String word = arguments.get(1);

            Automaton automaton = getAutomatonById(id);

            if (automaton == null) {
                System.out.println("Automaton with id " + id + " not found.");
                return;
            }

            boolean result = containsWord(word, automaton);

            if (result) {
                System.out.println("The word '" + word + "' is in the alphabet of the automaton.");
            } else {
                System.out.println("The word '" + word + "' is not in the alphabet of the automaton.");
            }
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private int parseId(String idString) {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid automaton ID. Please provide a valid integer ID.");
        }
    }

    private boolean containsWord(String word, Automaton automaton) {
        String generatedWord = automaton.generateWord();
        return generatedWord.contains(word);
    }

    private Automaton getAutomatonById(int id) {
        AutomatonList automatonList = AutomatonList.getInstance();
        return automatonList.getAutomatons().get(id);
    }
}