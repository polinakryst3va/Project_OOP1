import java.util.Set;
import java.util.Map;

public class NFA extends Automaton {

    public NFA(int id, Set<Integer> states, Set<Character> alphabet, Map<Integer, Map<Character, Set<Integer>>> transitions, int initialStates, Set<Integer> finalStates) {
        super(id, states, alphabet, transitions, initialStates, finalStates);
    }
}
