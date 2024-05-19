package main.java.cli.commands.files;
import main.java.exeptions.files.WritingToFIleException;
import main.java.realization.AutomatonParts.Edge;
import main.java.realization.AutomatonParts.Node;
import main.java.realization.models.Automaton;
import main.java.realization.models.AutomatonList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class FileAutomatonWriter {

    private String filePath;
    private String content;

    public FileAutomatonWriter(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getContent() {
        return content;
    }

    public void write() {
        StringBuilder contentInfo = new StringBuilder();
        Map<Integer, Automaton> automatons = AutomatonList.getInstance().getAutomatons();
        for (Map.Entry<Integer, Automaton> entry : automatons.entrySet()) {
            contentInfo.append("id=").append(entry.getKey()).append("\n");
            Map<Node, List<Edge>> currentAutomaton = entry.getValue().getAutomaton();
            for (Map.Entry<Node, List<Edge>> automatonEntry : currentAutomaton.entrySet()) {
                if (automatonEntry.getValue() == null || automatonEntry.getValue().isEmpty()) {
                    contentInfo.append(automatonEntry.getKey().getValue()).append("=\n");
                    continue;
                }
                for (Edge edge : automatonEntry.getValue()) {
                    contentInfo.append(automatonEntry.getKey().getValue()).append("=")
                            .append(edge.getTransition()).append(edge.getEndNode().getValue()).append("\n");
                }
            }
            contentInfo.append("-\n");
        }
        this.content = contentInfo.toString();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            writer.println(contentInfo.toString());
            System.out.println("Successfully saved to " + filePath);
        } catch (WritingToFIleException | IOException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }

    public void append(int id) {
        Automaton automaton = AutomatonList.getInstance().getAutomaton(id);
        StringBuilder contentInfo = new StringBuilder(content);
        contentInfo.append("id=").append(id).append("\n");
        Map<Node, List<Edge>> automatonMap = automaton.getAutomaton();
        for (Map.Entry<Node, List<Edge>> automatonEntry : automatonMap.entrySet()) {
            if (automatonEntry.getValue().isEmpty()) {
                contentInfo.append(automatonEntry.getKey().getValue()).append("=\n");
                continue;
            }
            for (Edge edge : automatonEntry.getValue()) {
                contentInfo.append(automatonEntry.getKey().getValue()).append("=")
                        .append(edge.getTransition()).append(edge.getEndNode().getValue()).append("\n");
            }
        }
        contentInfo.append("-\n");
        this.content = contentInfo.toString();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            writer.println(contentInfo.toString());
        } catch (WritingToFIleException | IOException ex) {
            System.err.println("Error appending to file: " + ex.getMessage());
        }
    }
}