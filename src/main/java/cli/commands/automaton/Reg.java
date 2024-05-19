package main.java.cli.commands.automaton;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.Automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.files.NoOpenFileException;
import java.util.*;


public class Reg extends DefaultCommand {

    private final AutomatonList automatonList = AutomatonList.getInstance();
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz123456789";

    @Override
    public void execute(List<String> arguments) {
        try {
            if (AutomatonManager.getInstance().getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }

            if (arguments.size() != 1) {
                throw new IllegalArgumentException("Error: Invalid number of arguments. Usage: reg <regex>");
            }

            String regex = arguments.get(0);

            if (!isValidRegex(regex)) {
                throw new IllegalArgumentException("Error: Invalid regular expression format. Only characters from the alphabet are allowed.");
            }

            Automaton regAutomaton = createAutomatonFromRegex(regex);
            if (regAutomaton == null) {
                System.out.println("Error: Failed to create automaton from regex.");
                return;
            }

            int newAutomatonId = automatonList.addAutomaton(regAutomaton);

            System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
        } catch (NoOpenFileException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isValidRegex(String regex) {
        for (char c : regex.toCharArray()) {
            if (ALPHABET.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    private Automaton createAutomatonFromRegex(String regex) {
        Automaton automaton = new Automaton(ALPHABET);

        int m = regex.length();
        Stack<Node> stack = new Stack<>();
        Node start = new Node("S");
        automaton.addNode(start);
        stack.push(start);

        String capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idIndex = 0;

        List<Node> endNodes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            char c = regex.charAt(i);
            if (c == '(') {
                Node newNode = new Node(Character.toString(capitals.charAt(idIndex)));
                idIndex++;
                automaton.addNode(newNode);
                stack.push(newNode);
            } else if (c == '+') {
                stack.pop();
            } else if (c == ')') {
                stack.pop();
            }else if (c == '*') {
                if (!stack.isEmpty()) {
                    Node prevNode = stack.pop();
                    if (!stack.isEmpty()) {
                        Node previousNode = stack.peek();
                        automaton.addEdge(previousNode, prevNode, "e");
                    }
                    automaton.addEdge(prevNode, prevNode, "e");
                    stack.push(prevNode);
                } else {
                    System.out.println("Error: Invalid regular expression.");
                    return null;
                }
            }else {
                Node newNode = new Node(Character.toString(capitals.charAt(idIndex)));
                idIndex++;
                automaton.addNode(newNode);
                automaton.addEdge(stack.peek(), newNode, Character.toString(c));
                stack.push(newNode);
            }
        }

        Node lastNode = stack.peek();
        endNodes.add(lastNode);
        automaton.addEndNode(lastNode);

        return automaton;
    }
}