//import java.io.*;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class Serialization {
//    private static int nextId = 1;
//
//    public static void saveAutomaton(anotherpackage.Automaton automaton, String filename) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//            writer.println("AutomatonID: " + nextId++);
//            writer.println("States: " + automaton.getStates());
//            writer.println("Alphabet: " + automaton.getAlphabet());
//            writer.println("Transitions: " + automaton.getTransitions());
//            writer.println("InitialStates: " + automaton.getInitialStates());
//            writer.println("FinalStates: " + automaton.getFinalStates());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static anotherpackage.Automaton loadAutomaton(String filename) {
//        anotherpackage.Automaton automaton = null;
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            Set<Integer> states = null;
//            Set<Integer> alphabet = null;
//            Map<Integer, Map<Character, Set<Integer>>> transitions = null;
//            Set<Integer> initialStates = null;
//            Set<Integer> finalStates = null;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(": ");
//                if (parts.length != 2) continue;
//                String key = parts[0].trim();
//                String value = parts[1].trim();
//                switch (key) {
//                    case "AutomatonID":
//                        int id = Integer.parseInt(value);
//                        break;
//                    case "States":
//                        states = parseSet(value);
//                        break;
//                    case "Alphabet":
//                        alphabet = parseSet(value);
//                        break;
//                    case "Transitions":
//                        transitions = parseTransitions(value);
//                        break;
//                    case "InitialStates":
//                        initialStates = parseSet(value);
//                        break;
//                    case "FinalStates":
//                        finalStates = parseSet(value);
//                        break;
//                }
//            }
//            if (states != null && alphabet != null && transitions != null && initialStates != null && finalStates != null) {
//                automaton = new anotherpackage.Automaton(nextId++, states, alphabet, transitions, initialStates, finalStates);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return automaton;
//    }
//
//    private static Set<Integer> parseSet(String str) {
//        Set<Integer> set = new HashSet<>();
//        String[] parts = str.split(",");
//        for (String part : parts) {
//            set.add(Integer.parseInt(part.trim()));
//        }
//        return set;
//    }
//
//    private static Map<Integer, Map<Character, Set<Integer>>> parseTransitions(String str) {
//        Map<Integer, Map<Character, Set<Integer>>> transitions = new HashMap<>();
//        String[] transitionStrings = str.split(";");
//        for (String transitionString : transitionStrings) {
//            String[] parts = transitionString.split(":");
//            if (parts.length == 3) {
//                int currentState = Integer.parseInt(parts[0].trim());
//                char symbol = parts[1].trim().charAt(0);
//                Set<Integer> nextStates = parseSet(parts[2].trim());
//                transitions.computeIfAbsent(currentState, k -> new HashMap<>())
//                        .computeIfAbsent(symbol, k -> new HashSet<>())
//                        .addAll(nextStates);
//            }
//        }
//        return transitions;
//    }
//}