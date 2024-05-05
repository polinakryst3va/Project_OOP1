package cli;

import cli.commands.*;

import java.util.*;

public class CLI {
    private static Map<Operations, Command> commands = new HashMap<>();

    static {
        commands.put(Operations.EXIT, new Exit());
        commands.put(Operations.OPEN, new Open());
        commands.put(Operations.HELP, new Help());
        commands.put(Operations.RECOGNIZE, new Recognize());
        commands.put(Operations.LIST, new ListCommand());
        commands.put(Operations.PRINT, new Print());
        commands.put(Operations.SAVE, new Save());
        commands.put(Operations.CLOSE, new Close());
    }

    public static void start() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print(">");
            String input = scanner.nextLine();
            String[] arguments = input.split(" ");
            commands.get(Operations.valueOf(arguments[0].toUpperCase())).execute(Arrays.stream(arguments).toList().subList(1, arguments.length));
        } while (true);
    }
}
