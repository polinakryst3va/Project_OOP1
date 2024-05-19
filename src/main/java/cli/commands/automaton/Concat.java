package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;

import java.util.List;

public class Concat extends DefaultCommand {

    public Concat() {
    }

    private final AutomatonList automatonList = AutomatonList.getInstance();

    @Override
    public void execute(List<String> arguments) {
        try {

            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 2) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: concat <id1> <id2>");
            }

            int id1, id2;
            try {
                id1 = Integer.parseInt(arguments.get(0));
                id2 = Integer.parseInt(arguments.get(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid argument(s). IDs must be integers.");
            }

            if (!automatonList.getAutomatons().containsKey(id1) || !automatonList.getAutomatons().containsKey(id2)) {
                throw new AutomatonNotFoundException("Error: Automaton with ID " + id1 + " or " + id2 + " not found.");
            }

            Automaton automaton1 = automatonList.getAutomaton(id1);
            Automaton automaton2 = automatonList.getAutomaton(id2);

            Automaton concatAutomaton = findConcatenation(automaton1, automaton2);

            int newAutomatonId = automatonList.addAutomaton(concatAutomaton);

            System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Automaton findConcatenation(Automaton automaton1, Automaton automaton2) {
        Automaton concatAutomaton = new Automaton(automaton1.getAlphabet());


        for (Node node : automaton1.getAutomaton().keySet()) {
            concatAutomaton.addNode(node);
            if (automaton1.isEndNode(node)) {
                concatAutomaton.addEndNode(node);
            }

            List<Edge> edges = automaton1.getAutomaton().get(node);
            for (Edge edge : edges) {
                concatAutomaton.addEdge(node, edge.getEndNode(), edge.getTransition());
            }
        }


        for (Node endNode : automaton1.getEndNodes()) {
            for (Node startNodeAutomaton2 : automaton2.getAutomaton().keySet()) {
                concatAutomaton.addEdge(endNode, startNodeAutomaton2, "");
            }
        }


        for (Node node : automaton2.getAutomaton().keySet()) {
            concatAutomaton.addNode(node);
            if (automaton2.isEndNode(node)) {
                concatAutomaton.addEndNode(node);
            }

            List<Edge> edges = automaton2.getAutomaton().get(node);
            for (Edge edge : edges) {
                concatAutomaton.addEdge(node, edge.getEndNode(), edge.getTransition());
            }
        }

        return concatAutomaton;
    }
}