package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.Automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import java.util.*;


public class Deterministic extends DefaultCommand {

    public Deterministic() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {

            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: deterministic <id>");
            }

            String idString = arguments.get(0);
            try {
                int automatonId = Integer.parseInt(idString);
                Automaton automaton = AutomatonList.getInstance().getAutomatons().get(automatonId);
                if (automaton == null) {
                    throw new AutomatonNotFoundException("Error: Automaton with ID " + automatonId + " not found.");
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
        } catch (NoOpenFileException | IllegalArgumentException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
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