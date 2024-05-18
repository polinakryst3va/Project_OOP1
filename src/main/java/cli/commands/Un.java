package main.java.cli.commands;
import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Un extends DefaultCommand {
    private final AutomatonList automatonList = AutomatonList.getInstance();

    public Un() {
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.out.println("Error: Invalid number of arguments. Usage: un <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(arguments.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument. ID must be an integer.");
            return;
        }

        if (!automatonList.getAutomatons().containsKey(id)) {
            System.out.println("Error: Automaton with ID " + id + " not found.");
            return;
        }

        Automaton automaton = automatonList.getAutomaton(id);
        Automaton unAutomaton = findUnreachableStates(automaton);

        int newAutomatonId = automatonList.addAutomaton(unAutomaton);

        System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
    }

    private Automaton findUnreachableStates(Automaton automaton) {
        Set<Node> reachableStates = findReachableStates(automaton);
        Automaton unAutomaton = new Automaton(automaton.getAlphabet());

        for (Node node : reachableStates) {
            unAutomaton.addNode(node);
            if (automaton.isEndNode(node)) {
                unAutomaton.addEndNode(node);
            }

            List<Edge> edges = automaton.getAutomaton().get(node);
            for (Edge edge : edges) {
                if (reachableStates.contains(edge.getEndNode())) {
                    unAutomaton.addEdge(node, edge.getEndNode(), edge.getTransition());
                }
            }
        }

        return unAutomaton;
    }

    private Set<Node> findReachableStates(Automaton automaton) {
        Set<Node> reachableStates = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        Set<Node> queue = new HashSet<>();

        Node startNode = new Node("S");

        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.iterator().next();
            queue.remove(node);

            if (!visited.contains(node)) {
                visited.add(node);
                reachableStates.add(node);

                List<Edge> edges = automaton.getAutomaton().get(node);
                if (edges != null) {
                    for (Edge edge : edges) {
                        queue.add(edge.getEndNode());
                    }
                }
            }
        }

        return reachableStates;
    }
}
