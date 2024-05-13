package main.java.anotherpackage;

import main.java.AutomatonParts.Node;

import java.util.*;

public class AutomatonList {

    private static AutomatonList singletonInstance;
    private Map<Integer, Automaton> automatons = new HashMap<>();
    private int nextAutomatonId = 1; // Поле за следващия идентификатор на автомата

    private AutomatonList() {}

    public Map<Integer, Automaton> getAutomatons() {
        return new HashMap<>(automatons);
    }

    public void addAutomaton(int id, Automaton automaton) {
        if (!automaton.getAutomaton().containsKey(new Node("S"))) {
            //greshenAvtomatException
        }
        if (automatons.containsKey(id)) {
           // id = generateUniqueAutomatonId();
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

    public Automaton getAutomaton(int id) {
        return automatons.get(id);
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
