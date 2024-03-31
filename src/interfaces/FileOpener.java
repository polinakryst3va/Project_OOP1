package interfaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {
    private String filename;
    private String content;

    public FileOpener(String filename) {
        this.filename = filename;
        this.content = "";
    }

    public String getContent() {
        return content;
    }

    public boolean openFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            content = sb.toString();
            System.out.println("Successfully opened " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            return false;
        }
    }
}
