package main.java.exeptions.files;

import main.java.exeptions.comands.AutomatonExeption;

public class NoOpenFileException extends AutomatonExeption {
    public NoOpenFileException(String message) {
        super(message);
    }
}
