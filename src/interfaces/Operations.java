package interfaces;

import java.io.Serializable;
import java.util.Set;

public interface Operations extends Serializable  {
    void save(String filename);
    public boolean isEmptyLanguage();
    public boolean isDeterministic();
    public Set<Integer> getStatesForTransition(int currentState, char symbol);
}
