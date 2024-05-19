package main.java.cli.commands.automaton;

import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.files.NoOpenFileException;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.cli.commands.execution.DefaultCommand;
import java.util.*;

public class Mutator extends DefaultCommand {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() < 1) {
                throw new IllegalArgumentException("Please provide the ID of the automaton to determinize.");
            }

            int automatonId;
            try {
                automatonId = Integer.parseInt(arguments.get(0));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid automaton ID. Please provide a valid integer ID.");
            }

            AutomatonList automatonList = AutomatonList.getInstance();
            Automaton nfa = automatonList.getAutomaton(automatonId);

            if (nfa == null) {
                throw new IllegalArgumentException("Automaton with ID " + automatonId + " does not exist.");
            }


            Deterministic deterministicCommand = new Deterministic();
            boolean isDeterministic = deterministicCommand.isAutomatonDeterministic(nfa);

            if (isDeterministic) {
                System.out.println("Automaton with ID " + automatonId + " is already deterministic.");
                return;
            }

            Automaton dfa = determinize(nfa);
            if (dfa == null) {
                System.out.println("Failed to determinize automaton with ID " + automatonId + ".");
                return;
            }

            int newAutomatonId = automatonList.addAutomaton(dfa);

            System.out.println("Automaton " + automatonId + " has been determinized.");
            System.out.println("New Automaton has been added with ID: " + newAutomatonId);
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Automaton determinize(Automaton nfa) {
        String alphabet = nfa.getAlphabet();
        Automaton dfa = new Automaton(alphabet);

        Map<Set<Node>, Node> dfaStates = new HashMap<>();
        Queue<Set<Node>> queue = new LinkedList<>();

        Set<Node> startState = new HashSet<>();
        startState.add(new Node("S"));
        queue.add(startState);
        Node dfaStartState = new Node(stateSetToString(startState));
        dfa.addNode(dfaStartState);
        dfaStates.put(startState, dfaStartState);

        while (!queue.isEmpty()) {
            Set<Node> currentStateSet = queue.poll();
            Node currentDfaState = dfaStates.get(currentStateSet);

            for (char c : alphabet.toCharArray()) {
                Set<Node> nextStateSet = new HashSet<>();
                for (Node node : currentStateSet) {
                    List<Edge> edges = nfa.getAutomaton().get(node);
                    if (edges != null) {
                        for (Edge edge : edges) {
                            if (edge.getTransition().equals(String.valueOf(c))) {
                                nextStateSet.add(edge.getEndNode());
                            }
                        }
                    }
                }

                if (!nextStateSet.isEmpty()) {
                    Node dfaNextState;
                    if (!dfaStates.containsKey(nextStateSet)) {
                        dfaNextState = new Node(stateSetToString(nextStateSet));
                        dfa.addNode(dfaNextState);
                        dfaStates.put(nextStateSet, dfaNextState);
                        queue.add(nextStateSet);
                    } else {
                        dfaNextState = dfaStates.get(nextStateSet);
                    }

                    dfa.addEdge(currentDfaState, dfaNextState, String.valueOf(c));

                    if (containsEndNode(nextStateSet, nfa.getEndNodes())) {
                        dfa.addEndNode(dfaNextState);
                    }
                }
            }
        }

        return dfa;
    }

    private String stateSetToString(Set<Node> stateSet) {
        List<String> sortedStateNames = new ArrayList<>();
        for (Node node : stateSet) {
            sortedStateNames.add(node.getValue());
        }
        Collections.sort(sortedStateNames);
        return String.join(",", sortedStateNames);
    }

    private boolean containsEndNode(Set<Node> stateSet, List<Node> endNodes) {
        for (Node endNode : endNodes) {
            if (stateSet.contains(endNode)) {
                return true;
            }
        }
        return false;
    }
}