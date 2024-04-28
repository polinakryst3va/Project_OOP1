import java.util.ArrayList;
import java.util.List;

public class AutomatonList {

    private static AutomatonList singletonInstance;
    private List<Integer> automatonIds;


    private AutomatonList() {
        this.automatonIds = new ArrayList<>();
    }

    public void addAutomatonId(int id) {
        automatonIds.add(id);
    }

    public static AutomatonList getInstance(){
        if(singletonInstance == null){
            singletonInstance = new AutomatonList();
        }
        return singletonInstance;
    }

    public void addAutomationId(int id){
        automatonIds.add(id);
    }

    public List<Integer> getAllAutomatonIds(){
        return new ArrayList<>(automatonIds);
    }
}
