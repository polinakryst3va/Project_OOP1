package main.java.cli;

public enum Operations {
    OPEN("open", "Opens a file and reads its contents."),
    CLOSE("close", "Closes the current file."),
    SAVE( "save", "Saves the contents in the current file."),
    SAVEAS("save as",  "Saves the contents in a directory, chosen by the user."),
    HELP("help", "Shows all possible commands."),
    EXIT("exit", "Exits the application."),
    LIST("list", "Outputs a List of IDs of all automatons read."),
    PRINT("print", "Displays information about all transitions in the automaton."),
    SAVE_ID_FILENAME("save automaton", "Saves an automaton to a file."),
    EMPTY("empty", "Checks if the language is empty."),
    DETERMINISTIC("deterministic", "Checks whether an automaton is deterministic."),
    RECOGNIZE("recognize", "Checks if a word is in the language."),
    UNION("union", "Finds the union of two automatons and creates a new automaton. Prints the ID of the new automaton."),
    CONCAT("concat", "Finds the concatenation of two automatons and creates a new automaton. Prints the ID of the new automaton."),
    UN("un", "Finds the positive envelope of an automaton and creates a new automaton. Prints the ID of the new automaton."),
    REG("reg", "Creates a new automaton based on a specified regular expression (Cliny's theorem). Prints the ID of the new automaton.");

    private String command;
    private String description;

    Operations(String command, String description) {
        this.command = command;
        this.description = description;

    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
