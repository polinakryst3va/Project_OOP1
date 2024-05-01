package cli.commands;

import anotherpackage.Automaton;
import anotherpackage.AutomatonList;
import cli.DefaultCommand;
import cli.Operations;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Recognize extends DefaultCommand {

    public Recognize() {
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 2) {
            System.out.println("Usage: recognize <id> <word>");
            return;
        }

        Integer id = Integer.valueOf(arguments.get(0));
        String word = arguments.get(1);

        Automaton automaton = getAutomatonById(id);

        if (automaton == null) {
            System.out.println("Automaton with id " + id + " not found.");
            return;
        }

        boolean result = recognizeWord(word, automaton);

        if (result) {
            System.out.println("The word '" + word + "' is in the alphabet of the automaton.");
        } else {
            System.out.println("The word '" + word + "' is not in the alphabet of the automaton.");
        }
    }

    private boolean recognizeWord(String word, Automaton automaton) {
        String generatedWord = automaton.generateWord();
        return generatedWord.equals(word);
    }

    private Automaton getAutomatonById(int id) {
        AutomatonList automatonList = AutomatonList.getInstance();
        return automatonList.getAutomatons().get(id);
    }
}

