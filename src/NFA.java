import java.util.Set;
import java.util.Map;

public class NFA {
    private Set<Integer> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
    private Set<Integer> startStates; //начални състояния
    private Set<Integer> finalStates; //крайни състояния

    public NFA(Set<Integer> states, Set<Character> alphabet, Map<Integer, Map<Character, Set<Integer>>> transitions, Set<Integer> startStates, Set<Integer> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.startStates = startStates;
        this.finalStates = finalStates;
    } //конструктор


    public void addStates(int state){
        states.add(state);
    }

    public void addTransition(int fromState, char ch, int toState){

    }
}
