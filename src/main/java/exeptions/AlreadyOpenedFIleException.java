package main.java.exeptions;

public class AlreadyOpenedFIleException extends RuntimeException{
    public AlreadyOpenedFIleException(String message) {
        super(message);
    }
}
