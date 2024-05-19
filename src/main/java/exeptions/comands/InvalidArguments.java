package main.java.exeptions.comands;

import main.java.exeptions.comands.AutomatonExeption;

public class InvalidArguments extends AutomatonExeption {
    public InvalidArguments(String message) {
        super(message);
    }
}
