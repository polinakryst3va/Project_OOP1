package interfaces;

import anotherpackage.Automaton;
import anotherpackage.AutomatonList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileOpener {
    private String filename;
    private String content;
    private String filePath;

    public FileOpener(String filename) {
        this.filename = filename;
        this.content = "";
        filePath = ".\\src\\";
    }

    public String getContent() {
        return content;
    }

    public boolean openFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.concat(filename)))) {
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
                            automaton.addEndNode(from);
                            continue;
                        }
                        transition = lineContent[1].substring(0, lineContent.length - 1);
                        to = new Node(String.valueOf(lineContent[1].charAt(lineContent.length - 1)));
                        automaton.addEdge(from, to, transition);
                    }
                }
            }
            content = sb.toString();

            System.out.println("Successfully opened " + filename);
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
}
