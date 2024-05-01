package interfaces;

public interface OperationsWithAutomaton {

    public void addInitialState(Node state);

    public void removeInitialState(Node state);

    public boolean isInitialState(Node state);

    public void clearInitialStates();

    public void addFinalState(Node state);

    public void removeFinalState(Node state);

    public boolean isFinalState(Node state);

    public void clearFinalStates();

}
