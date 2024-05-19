package main.java.cli.commands.automaton;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.files.NoOpenFileException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Un extends DefaultCommand {
    private final AutomatonList automatonList = AutomatonList.getInstance();

    public Un() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: un <id>");
            }

            int id;
            try {
                id = Integer.parseInt(arguments.get(0));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid argument. ID must be an integer.");
            }

            if (!automatonList.getAutomatons().containsKey(id)) {
                throw new AutomatonNotFoundException("Error: Automaton with ID " + id + " not found.");
            }

            Automaton automaton = automatonList.getAutomaton(id);
            Automaton unAutomaton = findUnreachableStates(automaton);

            int newAutomatonId = automatonList.addAutomaton(unAutomaton);

            System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
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
