import interfaces.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Automaton implements Operations {

    private int id;
    private Set<Integer> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
    private Set<Integer> initialStates; //начални състояния
    private Set<Integer> finalStates; //крайни състояния

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


    public static class AutomatonBuilder {
        private int id;
        private Set<Integer> states;  //състояния
        private Set<Character> alphabet; //азбука
        private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
        private Set<Integer> initialStates; //начални състояния
        private Set<Integer> finalStates; //крайни състояния

        public AutomatonBuilder(int id) {
            this.id = id;
        }

        public AutomatonBuilder withStates(Set<Integer> states) {
            this.states = states;
            return this;
        }

        public AutomatonBuilder withAlphabet(Set<Character> alphabet) {
            this.alphabet = alphabet;
            return this;
        }

        public AutomatonBuilder withTransitions(Map<Integer, Map<Character, Set<Integer>>> transitions) {
            this.transitions = transitions;
            return this;
        }

        public AutomatonBuilder withStartStates(int startStates) {
            this.initialStates = initialStates;
            return this;
        }

        public AutomatonBuilder withFinalStates(Set<Integer> finalStates) {
            this.finalStates = finalStates;
            return this;
        }

        public Automaton build() {
            return new Automaton(this);
        }
    }

    private Automaton() {
    };

    public Automaton(AutomatonBuilder builder){
        this.id = builder.id;
        this.states = builder.states;
        this.alphabet = builder.alphabet;
        this.finalStates = builder.finalStates;
        this.initialStates = builder.initialStates;
        this.transitions = builder.transitions;
    }
}
