package main.java.cli.commands.automaton;
import main.java.realization.AutomatonList;
import main.java.cli.commands.execution.DefaultCommand;
import main.java.cli.commands.files.AutomatonManager;
import main.java.exeptions.comands.AutomatonNotFoundException;
import main.java.exeptions.files.NoOpenFileException;
import java.util.List;

public class ListCommand extends DefaultCommand {
    public ListCommand() {
    }

    @Override
    public void execute(List<String> arguments) {
        try {
            AutomatonManager manager = AutomatonManager.getInstance();
            if(manager.getOpenedFile() == null) {
                throw new NoOpenFileException("Error: No file is currently open.");
            }
            AutomatonList automatonList = AutomatonList.getInstance();
            List<Integer> allAutomatonIds = automatonList.getAllAutomatonIds();

            if (allAutomatonIds.isEmpty()) {
                System.out.println("There are no automatons.");
            } else {
                System.out.println("List of automatons IDs:");
                for (Integer id : allAutomatonIds) {
                    System.out.print(id);
                    if (allAutomatonIds.indexOf(id) < allAutomatonIds.size() - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.print("\n");
        } catch (NoOpenFileException | AutomatonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}