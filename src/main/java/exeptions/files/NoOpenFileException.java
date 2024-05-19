package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonException;

public class NoOpenFileException extends AutomatonException {
    public NoOpenFileException(String message) {
        super(message);
    }
}
