package interfaces;

import java.io.Serializable;
import java.util.Set;

public interface OperationsWithAutomaton {

    public void addInitialState(int state);

    public void removeInitialState(int state);

    public boolean isInitialState(int state);

    public void clearInitialStates();

    public void addFinalState(int state);

    public void removeFinalState(int state);

    public boolean isFinalState(int state);

    public void clearFinalStates();

}
