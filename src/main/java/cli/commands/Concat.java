package main.java.cli.commands;

import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;

import java.util.List;

public class Concat extends DefaultCommand {

    public Concat() {
    }

    private final AutomatonList automatonList = AutomatonList.getInstance();

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 2) {
            System.out.println("Error: Invalid number of arguments. Usage: concat <id1> <id2>");
            return;
        }

        int id1, id2;
        try {
            id1 = Integer.parseInt(arguments.get(0));
            id2 = Integer.parseInt(arguments.get(1));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument(s). IDs must be integers.");
            return;
        }

        if (!automatonList.getAutomatons().containsKey(id1) || !automatonList.getAutomatons().containsKey(id2)) {
            System.out.println("Error: Automaton with ID " + id1 + " or " + id2 + " not found.");
            return;
        }

        Automaton automaton1 = automatonList.getAutomaton(id1);
        Automaton automaton2 = automatonList.getAutomaton(id2);

        Automaton concatAutomaton = findConcatenation(automaton1, automaton2);

        int newAutomatonId = automatonList.addAutomaton(concatAutomaton);

        System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
    }

    private Automaton findConcatenation(Automaton automaton1, Automaton automaton2) {
        Automaton concatAutomaton = new Automaton(automaton1.getAlphabet());

        // Копиране на преходите от първия автомат
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

        // Добавяне на преходи от крайните състояния на първия автомат към началното състояние на втория автомат
        for (Node endNode : automaton1.getEndNodes()) {
            for (Node startNodeAutomaton2 : automaton2.getAutomaton().keySet()) {
                concatAutomaton.addEdge(endNode, startNodeAutomaton2, ""); // Празен символ
            }
        }

        // Копиране на преходите от втория автомат
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