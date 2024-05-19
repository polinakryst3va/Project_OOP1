package main.java.realization.AutomatonParts;
import java.util.Objects;

public class Node {
    private String value;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getValue(), node.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
