package anotherpackage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutomatonManager {
    private Map<Integer, Automaton> automata; // Карта за съхранение на автоматите

    public AutomatonManager() {
        this.automata = new HashMap<>();
    }

    public void addAutomaton(int id, Automaton automaton) {
        automata.put(id, automaton);
    }

    public Automaton getAutomaton(int id) {
        return automata.get(id);
    }

    public void removeAutomaton(int id) {
        automata.remove(id);
    }

    public Set<Integer> getAllAutomatonIds() {
        return automata.keySet();
    }
}