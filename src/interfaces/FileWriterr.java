package interfaces;

import java.util.List;

public interface FileWriterr {
    void saveChanges(String content);

    void saveAs(String content, String filename);
}
