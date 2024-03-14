package interfaces;

import interfaces.AbstractAutomaton;

import java.io.Serializable;

public interface SerializableAutomaton extends Serializable , AbstractAutomaton {
    void save(String filename);
}
