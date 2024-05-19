package main.java.cli.commands.execution;
import main.java.cli.commands.automaton.Mutator;
import main.java.cli.commands.automaton.*;
import main.java.cli.commands.files.*;

import java.util.*;

public class CLI {
    private static Map<Operations, Command> commands = new HashMap<>();

    static {
        commands.put(Operations.OPEN, new Open());
        commands.put(Operations.CLOSE, new Close());
        commands.put(Operations.SAVE, new Save());
        commands.put(Operations.SAVEAS, new SaveAs());
        commands.put(Operations.HELP, new Help());
        commands.put(Operations.EXIT, new Exit());

        commands.put(Operations.LIST, new ListCommand());
        commands.put(Operations.PRINT, new Print());
        commands.put(Operations.EMPTY, new Empty());
        commands.put(Operations.DETERMINISTIC, new Deterministic());
        commands.put(Operations.RECOGNIZE, new Recognize());
        commands.put(Operations.UNION, new Union());
        commands.put(Operations.CONCAT, new Concat());
        commands.put(Operations.UN, new Un());
        commands.put(Operations.REG, new Reg());
        commands.put(Operations.MUTATOR, new Mutator());
        commands.put(Operations.FINITE, new IsLanguageFinite());
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(">");
                String input = scanner.nextLine();
                String[] arguments = input.split(" ");
                Operations operation = Operations.valueOf(arguments[0].toUpperCase());
                Command command = commands.get(operation);
                if (command != null) {
                    command.execute(Arrays.asList(arguments).subList(1, arguments.length));
                } else {
                    System.out.println("Error: Unknown command. Type 'help' for a list of available commands.");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Unknown command. Type 'help' for a list of available commands.");
            }
        }
    }
}
