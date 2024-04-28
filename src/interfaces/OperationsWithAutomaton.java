package interfaces;

import java.io.Serializable;
import java.util.Set;

public interface OperationsWithAutomaton {

    public void addInitialState(State state);

    public void removeInitialState(State state);

    public boolean isInitialState(State state);

    public void clearInitialStates();

    public void addFinalState(State state);

    public void removeFinalState(State state);

    public boolean isFinalState(State state);

    public void clearFinalStates();

}
