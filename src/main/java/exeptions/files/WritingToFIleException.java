package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonException;

public class WritingToFIleException extends AutomatonException {
    public WritingToFIleException(String message) {
        super(message);
    }
}
