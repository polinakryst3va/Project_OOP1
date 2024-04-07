import java.util.*;

public class Automaton {

    private int id;
    private Set<Integer> states;  //състояния
    private Set<Character> alphabet; //азбука
    private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
    private int initialStates; //начални състояния
    private Set<Integer> finalStates; //крайни състояния

    private static class NFABuilder{
        private int id;
        private Set<Integer> states;  //състояния
        private Set<Character> alphabet; //азбука
        private Map<Integer, Map<Character, Set<Integer>>> transitions; //преходи между състояния
        private int initialStates; //начални състояния
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
            this.initialStates = startStates;
            return this;
        }
        public NFABuilder withFinalStates(Set<Integer> finalStates) {
            this.finalStates = finalStates;
            return this;
        }

        public Automaton build(){
            return new Automaton();
        }
    }

    private Automaton() {
    };

    private Automaton(NFABuilder builder){
        this.id = builder.id;
        this.states = builder.states;
        this.alphabet = builder.alphabet;
        this.finalStates = builder.finalStates;
        this.initialStates = builder.initialStates;
        this.transitions = builder.transitions;
    }



}
