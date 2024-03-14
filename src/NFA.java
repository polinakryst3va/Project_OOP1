import java.util.*;

public class NFA {

    private int id;
    private Set<Integer> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
    private int startStates; //начални състояния
    private Set<Integer> finalStates; //крайни състояния

    private static class NFABuilder{
        private int id;
        private Set<Integer> states;  //състояния
        private Set<Character> alphabet; //азбука
        private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
        private int startStates; //начални състояния
        private Set<Integer> finalStates; //крайни състояния

        public NFABuilder(int id) {
            this.id = id;
        }

        public NFABuilder withStates(Set<Integer> states) {
            this.states = states;
            return this;
        }

        public NFABuilder withAlphabet(Set<Character> alphabet){
            this.alphabet = alphabet;
            return this;
        }
        public NFABuilder withTransitions(Map<Integer, Map<Character, Set<Integer>>> transitions){
            this.transitions = transitions;
            return this;
        }
        public NFABuilder withStartStates(int startStates){
            this.startStates = startStates;
            return this;
        }
        public NFABuilder withFinalStates(Set<Integer> finalStates) {
            this.finalStates = finalStates;
            return this;
        }

        public NFA build(){
            return new NFA();
        }
    }

    private NFA() {
    };

    private NFA(NFABuilder builder){
        this.id = builder.id;
        this.states = builder.states;
        this.alphabet = builder.alphabet;
        this.finalStates = builder.finalStates;
        this.startStates = builder.startStates;
        this.transitions = builder.transitions;
    }



}
