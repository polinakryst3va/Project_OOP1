package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.Automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Empty extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: empty <id>");
            }

            int id;
            try {
                id = Integer.parseInt(arguments.get(0));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid automaton ID. Please provide a valid integer ID.");
            }

            Automaton automaton = AutomatonList.getInstance().getAutomaton(id);

            if (automaton == null) {
                throw new AutomatonNotFoundException("Error: Automaton with id " + id + " not found.");
            }

            boolean isEmpty = isEmptyLanguage(automaton);
            if (isEmpty) {
                System.out.println("The language of automaton with id " + id + " is empty.");
            } else {
                System.out.println("The language of automaton with id " + id + " is not empty.");
            }
        } catch (NoOpenFileException | IllegalArgumentException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isEmptyLanguage(Automaton automaton) {
        Set<Node> reachableStates = new HashSet<>();
        for (Node startNode : automaton.getAutomaton().keySet()) {
            exploreReachableStates(startNode, reachableStates, automaton);
        }
        for (Node endNode : automaton.getEndNodes()) {
            if (reachableStates.contains(endNode)) {
                return false;
            }
        }
        return true;
    }

    private void exploreReachableStates(Node currentNode, Set<Node> reachableStates, Automaton automaton) {
        if (reachableStates.contains(currentNode)) {
            return;
        }
        reachableStates.add(currentNode);

        List<Edge> edges = automaton.getAutomaton().get(currentNode);
        if (edges != null) {
            for (Edge edge : edges) {
                exploreReachableStates(edge.getEndNode(), reachableStates, automaton);
            }
        }
    }
}