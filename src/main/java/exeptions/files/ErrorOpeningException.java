package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonException;

public class ErrorOpeningException extends AutomatonException {
    public ErrorOpeningException(String message) {
        super(message);
    }
}