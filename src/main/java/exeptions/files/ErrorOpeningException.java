package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonExeption;

public class ErrorOpeningException extends AutomatonExeption {
    public ErrorOpeningException(String message) {
        super(message);
    }
}