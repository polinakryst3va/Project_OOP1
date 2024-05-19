package main.java.realization;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import java.util.*;

public class Automaton {
    private Map<Node, List<Edge>> automaton = new HashMap<>();
    private String alphabet;
    private List<Node> endNodes = new ArrayList<>();

    public List<Node> getEndNodes() {
        return endNodes;
    }


    public Automaton(String alphabet) {
        this.alphabet = alphabet;
    }

    public Map<Node, List<Edge>> getAutomaton() {
        return automaton;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public String generateWord() {
        Set<Node> visited = new LinkedHashSet<>();
        Queue<Node> queue = new LinkedList<>();

        Node start = new Node("S");

        queue.add(start);
        visited.add(start);

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            List<Edge> edges = automaton.get(node);
            if (edges != null) {
                for (Edge edge : edges) {
                    if (!visited.contains(edge.getEndNode())) {
                        visited.add(edge.getEndNode());
                        queue.add(edge.getEndNode());
                        result.append(edge.getTransition());
                        if (isEndNode(edge.getEndNode())) {
                            return result.toString();
                        }
                    }
                }
            }
        }

        return result.toString();
    }

    public void addNode(Node node) {
        automaton.put(node, new ArrayList<>());
    }

    public void addEndNode(Node node) {
        if (!endNodes.contains(node)) {
            endNodes.add(node);
        }
    }



    public boolean isEndNode(Node node) {
        return endNodes.contains(node);
    }

    public void addEdge(Node from, Node to, String transition) {
        if (transition == null) {

            transition = "";
        } else if (transition.isEmpty()) {

            transition = "";
        }

        if (!automaton.containsKey(from)) {
            automaton.put(from, new ArrayList<>());
        }

        if (!automaton.containsKey(to)) {
            automaton.put(to, new ArrayList<>());
        }

        automaton.get(from).add(new Edge(transition, to));
    }

    @Override
    public String toString() {
        StringBuilder automatonInfo = new StringBuilder();

        int index = 1;
        for (Node node : automaton.keySet()) {
            for (Edge edge : automaton.get(node)) {
                automatonInfo
                        .append(index++)
                        .append(". ")
                        .append(node.getValue())
                        .append(" -> ")
                        .append(edge.getTransition())
                        .append(edge.getEndNode().getValue())
                        .append("\n");
            }
        }

        for (Node node : endNodes) {
            automatonInfo
                    .append(index++)
                    .append(". ")
                    .append(node.getValue())
                    .append(" -> ")
                    .append("\n");
        }

        return automatonInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Automaton)) return false;
        Automaton automaton1 = (Automaton) o;
        return Objects.equals(getAutomaton(), automaton1.getAutomaton()) && Objects.equals(getAlphabet(), automaton1.getAlphabet()) && Objects.equals(endNodes, automaton1.endNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAutomaton(), getAlphabet(), endNodes);
    }
}
