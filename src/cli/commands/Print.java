package cli.commands;
import anotherpackage.Automaton;
import anotherpackage.AutomatonList;
import cli.DefaultCommand;

import java.util.List;

public class Print extends DefaultCommand {

    public Print() {
    }

    @Override
    public void execute(List<String> arguments) {
        if (arguments.size() != 1) {
            System.out.println("Usage: print <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(arguments.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format.");
            return;
        }

        Automaton automaton = getAutomatonById(id);

        if (automaton == null) {
            System.out.println("Automaton with id " + id + " not found.");
            return;
        }

        System.out.println(automaton.toString());
    }

    private Automaton getAutomatonById(int id) {
        AutomatonList automatonList = AutomatonList.getInstance();
        return automatonList.getAutomatons().get(id);
    }

}
