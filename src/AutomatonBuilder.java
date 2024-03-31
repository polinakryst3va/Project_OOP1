import java.util.Map;
import java.util.Set;

public class AutomatonBuilder {
    private final int id;
    private final Set<Integer> states;
    private final Set<Character> alphabet;
    private final Map<Integer, Map<Character, Set<Integer>>> transitions;
    private final Set<Integer> initialStates;
    private final Set<Integer> finalStates;

    public AutomatonBuilder(int id, Set<Integer> states, Set<Character> alphabet,
                            Map<Integer, Map<Character, Set<Integer>>> transitions,
                            Set<Integer> initialStates, Set<Integer> finalStates) {
        this.id = id;
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialStates = initialStates;
        this.finalStates = finalStates;
    }

    public Automaton build() {
        return new Automaton(id, states, alphabet, transitions, initialStates, finalStates);
    }
}
