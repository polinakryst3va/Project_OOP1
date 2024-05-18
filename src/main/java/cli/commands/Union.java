package main.java.cli.commands;

import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;
import java.util.List;


public class Union extends DefaultCommand {
    private final AutomatonList automatonList = AutomatonList.getInstance();

    public Union() {
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 2) {
            System.out.println("Error: Invalid number of arguments. Usage: empty <id>");
            return;
        }

        int id1, id2;
        try {
            id1 = Integer.parseInt(arguments.get(0));
            id2 = Integer.parseInt(arguments.get(1));
        } catch (NumberFormatException e) {
            System.out.println("Error: no such ids");
            return;
        }

        if (!automatonList.getAutomatons().containsKey(id1) || !automatonList.getAutomatons().containsKey(id2)) {
            System.out.println("No automaton wiht id " + id1 + "or" + id2 + "found");
            return;
        }

        Automaton automaton1 = automatonList.getAutomaton(id1);
        Automaton automaton2 = automatonList.getAutomaton(id2);

        Automaton unionAutomaton = findUnion(automaton1, automaton2);

        int newAutomatonId = automatonList.addAutomaton(unionAutomaton);

        System.out.println("New automaton with ID : " + newAutomatonId + "is created!");
    }

    private Automaton findUnion(Automaton automaton1, Automaton automaton2) {

        Automaton unionAutomaton = new Automaton(automaton1.getAlphabet());

        for (Node node : automaton1.getAutomaton().keySet()) {
            unionAutomaton.addNode(node);
            if (automaton1.isEndNode(node)) {
                unionAutomaton.addEndNode(node);
            }
        }


        for (Node node : automaton2.getAutomaton().keySet()) {
            unionAutomaton.addNode(node);
            if (automaton2.isEndNode(node)) {
                unionAutomaton.addEndNode(node);
            }
        }

        for (Node from : automaton1.getAutomaton().keySet()) {
            List<Edge> edges = automaton1.getAutomaton().get(from);
            for (Edge edge : edges) {
                Node to = edge.getEndNode();
                String transition = edge.getTransition();
                unionAutomaton.addEdge(from, to, transition);
            }
        }

        for (Node from : automaton2.getAutomaton().keySet()) {
            List<Edge> edges = automaton2.getAutomaton().get(from);
            for (Edge edge : edges) {
                Node to = edge.getEndNode();
                String transition = edge.getTransition();
                unionAutomaton.addEdge(from, to, transition);
            }
        }

        return unionAutomaton;
    }
}

