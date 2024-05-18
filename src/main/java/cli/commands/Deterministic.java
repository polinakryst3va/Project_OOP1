package main.java.cli.commands;
import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;

import java.util.*;


public class Deterministic extends DefaultCommand {

    public Deterministic() {
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.err.println("Error: Invalid number of arguments. Usage: deterministic <id>");
            return;
        }

        String idString = arguments.get(0);
        try {
            int automatonId = Integer.parseInt(idString);
            Automaton automaton = AutomatonList.getInstance().getAutomatons().get(automatonId);
            if (automaton == null) {
                System.err.println("Error: Automaton with ID " + automatonId + " not found.");
                return;
            }

            boolean isDeterministic = isAutomatonDeterministic(automaton);
            if (isDeterministic) {
                System.out.println("Automaton with ID " + automatonId + " is deterministic.");
            } else {
                System.out.println("Automaton with ID " + automatonId + " is not deterministic.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid automaton ID. Please provide a valid integer ID.");
        }
    }

    public boolean isAutomatonDeterministic(Automaton automaton) {
        Map<Node, List<Edge>> automatonMap = automaton.getAutomaton();

        for (Map.Entry<Node, List<Edge>> entry : automatonMap.entrySet()) {
            List<Edge> edges = entry.getValue();
            List<String> transitions = new ArrayList<>();
            for (Edge edge : edges) {
                transitions.add(edge.getTransition());
            }
            long duplicateCount = transitions.stream().distinct().count();
            if (duplicateCount < transitions.size()) {
                return false;
            }
        }
        return true;
    }
}