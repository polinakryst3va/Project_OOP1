package cli;

import java.util.*;

public class CLI {
    private static Map<Operations, Command> commands = new HashMap<>();

    static {
        commands.put(Operations.EXIT, new Exit());
        commands.put(Operations.OPEN, new Open());
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
