package main.java.cli.commands;

import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Empty extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.err.println("Error: Invalid number of arguments. Usage: empty <id>");
            return;
        }

        int id = Integer.parseInt(arguments.get(0));
        Automaton automaton = AutomatonList.getInstance().getAutomaton(id);

        if (automaton == null) {
            System.err.println("Error: Automaton with id " + id + " not found.");
            return;
        }

        boolean isEmpty = isEmptyLanguage(automaton);
        if (isEmpty) {
            System.out.println("The language of automaton with id " + id + " is empty.");
        } else {
            System.out.println("The language of automaton with id " + id + " is not empty.");
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
