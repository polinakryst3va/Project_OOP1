package cli.commands;
import anotherpackage.Automaton;
import anotherpackage.AutomatonList;
import AutomatonParts.Edge;
import AutomatonParts.Node;
import java.io.*;
import java.util.Map;

public class AutomatonManager {
    private static AutomatonManager instance = null;
    private File openedFile = null;
    private Automaton automaton;

    private AutomatonManager() {
        // Private constructor
    }

    public static AutomatonManager getInstance() {
        if (instance == null) {
            instance = new AutomatonManager();
        }
        return instance;
    }

    public File getOpenedFile() {
        return openedFile;
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public void open(File file){
        FileOpener fileOpener = new FileOpener(file.getName());
        if(fileOpener.openFile()) {
            this.openedFile = file;
        }
    }


    public void save() throws IOException {
        if (openedFile == null) {
            throw new FileNotFoundException("No file open to save!");
        }

        if (automaton == null) {
            throw new IllegalStateException("No automaton to save!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(openedFile))) {
            writer.write("Alphabet: " + automaton.getAlphabet());
            writer.newLine();

            for (Node node : automaton.getAutomaton().keySet()) {
                for (Edge edge : automaton.getAutomaton().get(node)) {
                    writer.write(node.getValue() + " -> " + edge.getTransition() + edge.getEndNode().getValue());
                    writer.newLine();
                }
            }
            writer.write("End Nodes:");
            writer.newLine();
            for (Node node : automaton.getEndNodes()) {
                writer.write(node.getValue());
                writer.newLine();
            }

            System.out.println("Automaton successfully saved to " + openedFile.getName());
        } catch (IOException e) {
            System.err.println("Error saving automaton: " + e.getMessage());
            throw e;
        }
    }

    private void loadAutomaton(File file) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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

            System.out.println("Successfully opened " + file.getName());
            for (Map.Entry<Integer, Automaton> entrySet : AutomatonList.getInstance().getAutomatons().entrySet()) {
                System.out.println(entrySet.getKey() + ". ");
                System.out.println(entrySet.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
    public void close() {
        if (openedFile != null) {
            System.out.println("Successfully closed " + openedFile.getName());
            this.openedFile = null;
            this.automaton = null;
        } else {
            System.err.println("Error: No file is currently open.");
        }
    }
}
