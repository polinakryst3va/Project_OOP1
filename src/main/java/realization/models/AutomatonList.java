package main.java.realization.models;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.comands.InvalidAutomatonException;
import main.java.realization.AutomatonParts.Node;

import java.util.*;

public class AutomatonList {

    private static AutomatonList singletonInstance;
    private Map<Integer, Automaton> automatons = new HashMap<>();
    private int nextAutomatonId = 1;

    private AutomatonList() {}

    public Map<Integer, Automaton> getAutomatons() {
        return new HashMap<>(automatons);
    }

    public void addAutomaton(int id, Automaton automaton) throws InvalidAutomatonException {
        if (!automaton.getAutomaton().containsKey(new Node("S"))) {
            throw new InvalidAutomatonException("Automaton must contain the start node - S.");
        }
        if (automatons.containsKey(id)) {
            throw new IllegalArgumentException("Automaton with ID " + id + " already exists.");
        }
        automatons.put(id, automaton);
    }

    public void setAutomatons(Map<Integer, Automaton> automatons) {
        this.automatons = automatons;
    }

    public static AutomatonList getInstance(){
        if(singletonInstance == null){
            singletonInstance = new AutomatonList();
        }
        return singletonInstance;
    }

    public Automaton getAutomaton(int id) throws AutomatonNotFoundException {
        Automaton automaton = automatons.get(id);
        if (automaton == null) {
            throw new AutomatonNotFoundException("Automaton with ID " + id + " not found.");
        }
        return automaton;
    }

    public List<Integer> getAllAutomatonIds(){
        return new ArrayList<>(automatons.keySet());
    }

    public int addAutomaton(Automaton automaton) {
        int newAutomatonId = generateUniqueAutomatonId();
        automatons.put(newAutomatonId, automaton);
        return newAutomatonId;
    }

    private int generateUniqueAutomatonId() {
        while (automatons.containsKey(nextAutomatonId)) {
            nextAutomatonId++;
        }
        return nextAutomatonId;
    }
}
