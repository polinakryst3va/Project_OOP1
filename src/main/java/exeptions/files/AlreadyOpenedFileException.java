package main.java.exeptions.files;

public class AlreadyOpenedFileException extends RuntimeException{
    public AlreadyOpenedFileException(String message) {
        super(message);
    }
}
