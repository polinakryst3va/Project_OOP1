import interfaces.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Automaton implements Operations {

    private int id;
    private Set<Integer> states;  // състояния
    private Set<Character> alphabet; // азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; // преходи между състояния
    private Set<Integer> initialStates; // начални състояния
    private Set<Integer> finalStates; // крайни състояния

    public Automaton(int id, Set<Integer> states, Set<Character> alphabet,
                     Map<Integer, Map<Character, Set<Integer>>> transitions,
                     Set<Integer> initialStates, Set<Integer> finalStates) {
        this.id = id;
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialStates = initialStates;
        this.finalStates = finalStates;
    }


    private Automaton() {
    };

    public int getId() {
        return id;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public Map<Integer, Map<Character, Set<Integer>>> getTransitions() {
        return transitions;
    }

    public Set<Integer> getInitialStates() {
        return initialStates;
    }

    public Set<Integer> getFinalStates() {
        return finalStates;
    }

    @Override
    public void save(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Automaton ID: " + getId() + "\n");
            writer.write("States: " + getStates() + "\n");
            writer.write("Alphabet: " + getAlphabet() + "\n");
            writer.write("Transitions: " + getTransitions() + "\n");
            writer.write("Initial States: " + getInitialStates() + "\n");
            writer.write("Final States: " + getFinalStates() + "\n");
            System.out.println("Автоматът е записан успешно във файл " + filename);
        } catch (IOException e) {
            System.err.println("Грешка при записването на автомата във файл: " + e.getMessage());
        }
    }

    @Override
    public boolean isEmptyLanguage() {
        if (initialStates.isEmpty()) {
            return true;
        }

        if (finalStates.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDeterministic() {
        for (int state : states) {
            for (char symbol : alphabet) {
                Set<Integer> nextStates = getStatesForTransition(state, symbol);
                if (nextStates == null || nextStates.size() != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Set<Integer> getStatesForTransition(int currentState, char symbol) {
        Map<Character, Set<Integer>> transitionsFromCurrentState = transitions.get(currentState);
        if (transitionsFromCurrentState != null) {
            return transitionsFromCurrentState.get(symbol);
        }
        return null;
    }



}
