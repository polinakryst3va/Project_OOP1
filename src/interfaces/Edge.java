package interfaces;

import java.util.Objects;

public class Edge {
    private String transition;
    private Node endNode;

    public Edge(String transition, Node endNode) {
        this.transition = transition;
        this.endNode = endNode;
    }

    public String getTransition() {
        return transition;
    }

    public Node getEndNode() {
        return endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Objects.equals(getTransition(), edge.getTransition()) && Objects.equals(getEndNode(), edge.getEndNode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransition(), getEndNode());
    }
}
