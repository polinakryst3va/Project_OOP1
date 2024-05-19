package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import java.util.*;

public class IsLanguageFinite extends DefaultCommand {

    private final AutomatonList automatonList = AutomatonList.getInstance();

    public IsLanguageFinite() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: finite <id>");
            }

            int id;
            try {
                id = Integer.parseInt(arguments.get(0));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid automaton ID. Please provide a valid integer ID.");
            }

            Automaton automaton = automatonList.getAutomaton(id);

            if (automaton == null) {
                throw new AutomatonNotFoundException("Error: Automaton with ID " + id + " not found.");
            }

            boolean isFinite = isLanguageFinite(automaton);

            if (isFinite) {
                System.out.println("The language of automaton with ID " + id + " is finite.");
            } else {
                System.out.println("The language of automaton with ID " + id + " is infinite.");
            }
        } catch (NoOpenFileException | IllegalArgumentException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isLanguageFinite(Automaton automaton) {
        Set<Node> reachableStates = findReachableStates(automaton);

        return !hasCycle(automaton, reachableStates);
    }

    private Set<Node> findReachableStates(Automaton automaton) {

        Set<Node> reachableStates = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node startState = new Node("S");

        queue.add(startState);
        reachableStates.add(startState);

        while (!queue.isEmpty()) {
            Node currentState = queue.poll();
            List<Edge> edges = automaton.getAutomaton().get(currentState);
            if (edges != null) {
                for (Edge edge : edges) {
                    Node nextState = edge.getEndNode();
                    if (!reachableStates.contains(nextState)) {
                        reachableStates.add(nextState);
                        queue.add(nextState);
                    }
                }
            }
        }

        return reachableStates;
    }

    private boolean hasCycle(Automaton automaton, Set<Node> reachableStates) {
        Set<Node> visited = new HashSet<>();
        Set<Node> recStack = new HashSet<>();

        for (Node node : reachableStates) {
            if (detectCycle(automaton, node, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(Automaton automaton, Node node, Set<Node> visited, Set<Node> recStack) {
        if (recStack.contains(node)) {
            return true;
        }
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        recStack.add(node);

        List<Edge> edges = automaton.getAutomaton().get(node);
        if (edges != null) {
            for (Edge edge : edges) {
                if (detectCycle(automaton, edge.getEndNode(), visited, recStack)) {
                    return true;
                }
            }
        }

        recStack.remove(node);
        return false;
    }
}