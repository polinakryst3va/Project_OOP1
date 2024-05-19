package main.java.cli.commands.files;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import main.java.realization.AutomatonParts.Node;
import main.java.exeptions.files.CreatingFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    public boolean openFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            createNewFile(filePath);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            String[] lineContent;
            int id = 0;
            Node from = null, to = null;
            String transition = "";
            Automaton automaton = new Automaton("abcdefghijklmnopqrstuvwxyz123456789");

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");

                if (line.equals("-")) {
                    AutomatonList.getInstance().addAutomaton(id, automaton);
                    automaton = new Automaton("abcdefghijklmnopqrstuvwxyz123456789");
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
            return true;
        }
    }

    public void createNewFile(String filePath) throws CreatingFileException, IOException {
        File file = new File(filePath);
        if (!file.createNewFile()) {
            throw new CreatingFileException("Failed to create new file: " + filePath);
        }
    }
}