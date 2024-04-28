package anotherpackage;

import interfaces.OperationsWithAutomaton;
import interfaces.State;

import java.io.*;
import java.util.*;

public class Automaton implements OperationsWithAutomaton, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Set<State> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<State, Map<Character, Set<State>>> transitions; //преходи между състояния
    private Set<State> initialStates; //начални състояния
    private Set<State> finalStates; //крайни състояния

    public Automaton(int id, Set<State> states, Set<Character> alphabet, Map<State, Map<Character, Set<State>>> transitions, Set<State> initialStates, Set<State> finalStates) {
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

    public Set<State> getStates() {
        return states;
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public Map<State, Map<Character, Set<State>>> getTransitions() {
        return transitions;
    }

    public Set<State> getInitialStates() {
        return initialStates;
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public void saveAutomaton(String filename) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Automaton loadAutomaton(String filename) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Automaton) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addInitialState(State state) {
        initialStates.add(state);
    }

    @Override
    public void removeInitialState(State state) {
        initialStates.remove(state);
    }

    @Override
    public boolean isInitialState(State state) {
        return initialStates.contains(state);
    }

    @Override
    public void clearInitialStates() {
        initialStates.clear();
    }

    @Override
    public void addFinalState(State state) {
        finalStates.add(state);
    }

    @Override
    public void removeFinalState(State state) {
        finalStates.remove(state);
    }

    @Override
    public boolean isFinalState(State state) {
       return finalStates.contains(state);
    }

    @Override
    public void clearFinalStates() {
        finalStates.clear();
    }
}
