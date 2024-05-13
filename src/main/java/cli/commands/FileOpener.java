package main.java.cli.commands;
import main.java.anotherpackage.Automaton;
import main.java.anotherpackage.AutomatonList;
import main.java.AutomatonParts.Node;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileOpener {
    private String filePath;
    private static String content;
    private AutomatonManager automatonManager = AutomatonManager.getInstance();

    public FileOpener(String filePath) {
        this.filePath = filePath;
        this.content = "";

    }

    public static String getContent() {
        return content;
    }

    public boolean openFile() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            String[] lineContent;
            int id = 0;
            Node from = null, to = null;
            String transition = "";
            Automaton automaton = new Automaton("abcd");
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");

                if (line.equals("-")) {
                    AutomatonList.getInstance().addAutomaton(id, automaton);
                    automaton = new Automaton("abcd");
                    continue;
                }

                lineContent = line.split("=");

                switch (lineContent[0].toLowerCase()) {
                    case "id" -> {
                        id = Integer.parseInt(lineContent[1]);
                    }
                    default -> {
                        from = new Node(lineContent[0]);
                        if (lineContent.length == 1) {
                            automaton.addNode(from);
                            automaton.addEndNode(from);
                            continue;
                        }
                        transition = lineContent[1].substring(0, lineContent[1].length() - 1);
                        to = new Node(String.valueOf(lineContent[1].charAt(lineContent[1].length() - 1)));
                        automaton.addEdge(from, to, transition);
                    }
                }
            }
            content = sb.toString();

            System.out.println("Successfully opened " + filePath);
            //SAMO PROBNO!!!!!!!!!!!!!!!!!!!!!!!!!
            for (Map.Entry<Integer, Automaton> entrySet : AutomatonList.getInstance().getAutomatons().entrySet()) {
                System.out.println(entrySet.getKey() + ". ");
                System.out.println(entrySet.getValue());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            return false;
        }
    }

    public boolean closeFile() {
        try {
            content = "";
            System.out.println("Successfully closed the current document.");
            return true;
        } catch (Exception e) {
            System.err.println("Error closing file: " + e.getMessage());
            return false;
        }
    }


}