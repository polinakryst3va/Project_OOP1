import interfaces.OperationsWithAutomaton;

import java.util.*;

public class Automaton implements OperationsWithAutomaton {

    private int id;
    private Set<Integer> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
    private Set<Integer> initialStates; //начални състояния
    private Set<Integer> finalStates; //крайни състояния

    public Automaton(int id, Set<Integer> states, Set<Integer> alphabet, Map<Integer, Map<Character, Set<Integer>>> transitions, Set<Integer> initialStates, Set<Integer> finalStates) {
        this.id = id;
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialStates = initialStates;
        this.finalStates = finalStates;
    }

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
    public void addInitialState(int state) {
        initialStates.add(state);
    }

    @Override
    public void removeInitialState(int state) {
        initialStates.remove(state);
    }

    @Override
    public boolean isInitialState(int state) {
        return initialStates.contains(state);
    }

    @Override
    public void clearInitialStates() {
        initialStates.clear();
    }

    @Override
    public void addFinalState(int state) {
        finalStates.add(state);
    }

    @Override
    public void removeFinalState(int state) {
        finalStates.remove(state);
    }

    @Override
    public boolean isFinalState(int state) {
       return finalStates.contains(state);
    }

    @Override
    public void clearFinalStates() {
        finalStates.clear();
    }
}
