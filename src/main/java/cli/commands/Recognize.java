package main.java.cli.commands;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;
import java.util.List;

public class Recognize extends DefaultCommand {

    public Recognize() {
    }


    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 2) {
            System.out.println("Usage: recognize <id> <word>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(arguments.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format.");
            return;
        }

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

