import interfaces.FileWriterr;

import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter implements FileWriterr {

    private String filename;

    public XMLWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveChanges(String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("Successfully saved " + filename);
        } catch (IOException e) {
            System.err.println("Error saving changes to file " + e.getMessage());
        }
    }

    @Override
    public void saveAs(String content, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            System.out.println("Successfully saved another  " + filename);
        } catch (IOException e) {
            System.err.println("Error saving changes to file " + e.getMessage());
        }
    }
}
