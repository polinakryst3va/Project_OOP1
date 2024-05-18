package main.java.cli.commands;

import main.java.AutomatonParts.Edge;
import main.java.AutomatonParts.Node;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;

import java.util.*;


public class Reg extends DefaultCommand {

    private final AutomatonList automatonList = AutomatonList.getInstance();

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.out.println("Error: Invalid number of arguments. Usage: reg <regex>");
            return;
        }

        String regex = arguments.get(0);
        Automaton regAutomaton = createAutomatonFromRegex(regex);
        if (regAutomaton == null) {
            System.out.println("Error: Failed to create automaton from regex.");
            return;
        }

        int newAutomatonId = automatonList.addAutomaton(regAutomaton);

        System.out.println("New automaton with ID: " + newAutomatonId + " is created!");
    }


    private Automaton createAutomatonFromRegex(String regex) {
        Automaton automaton = new Automaton("abcdefghijklmnopqrstuvwxyz0123456789");

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