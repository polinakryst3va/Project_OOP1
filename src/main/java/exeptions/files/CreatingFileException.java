package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonException;

public class CreatingFileException extends AutomatonException {
    public CreatingFileException(String message) {
        super(message);
    }
}
