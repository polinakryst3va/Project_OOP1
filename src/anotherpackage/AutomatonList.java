package anotherpackage;

import AutomatonParts.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomatonList {

    private static AutomatonList singletonInstance;
    private Map<Integer, Automaton> automatons = new HashMap<>();


    private AutomatonList() {}

    public Map<Integer, Automaton> getAutomatons() {
        return new HashMap<>(automatons);
    }

    public void addAutomaton(int id, Automaton automaton) {
        if (!automaton.getAutomaton().containsKey(new Node("S"))) {
            //greshenAvtomatException
        }
        if (automatons.containsKey(id)) {
            //greshnoIdException
        }
        automatons.put(id, automaton);
    }

    public static AutomatonList getInstance(){
        if(singletonInstance == null){
            singletonInstance = new AutomatonList();
        }
        return singletonInstance;
    }

    public List<Integer> getAllAutomatonIds(){
        return new ArrayList<>(automatons.keySet());
    }
}
