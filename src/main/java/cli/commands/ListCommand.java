package main.java.cli.commands;
import main.java.anotherpackage.AutomatonList;
import main.java.cli.DefaultCommand;
import java.util.List;

public class ListCommand extends DefaultCommand {
    public ListCommand() {
    }

    @Override
    public void execute(List<String> arguments) {

        AutomatonManager manager = AutomatonManager.getInstance();
        if(manager.getOpenedFile() == null) {
            System.err.println("no opened file");
            return;
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
    }
}