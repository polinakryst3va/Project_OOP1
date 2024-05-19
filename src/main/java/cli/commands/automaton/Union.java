package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.Automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;


public class Union extends DefaultCommand {
    private final AutomatonList automatonList = AutomatonList.getInstance();

    public Union() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 2) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: union <id1> <id2>");
            }

            int id1, id2;
            try {
                id1 = Integer.parseInt(arguments.get(0));
                id2 = Integer.parseInt(arguments.get(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Invalid argument. IDs must be integers.");
            }

            if (!automatonList.getAutomatons().containsKey(id1) || !automatonList.getAutomatons().containsKey(id2)) {
                throw new IllegalArgumentException("Error: Automaton with ID " + id1 + " or " + id2 + " not found.");
            }

            Automaton automaton1 = automatonList.getAutomaton(id1);
            Automaton automaton2 = automatonList.getAutomaton(id2);

            Automaton unionAutomaton = findUnion(automaton1, automaton2);

            int newAutomatonId = automatonList.addAutomaton(unionAutomaton);

            System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
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