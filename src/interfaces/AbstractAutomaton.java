package interfaces;

import java.util.List;

public interface AbstractAutomaton {
    Integer getId();
    List<Character> getAlphabet();
    List<Integer> getStates();
    List<String> getTransitions();
}
