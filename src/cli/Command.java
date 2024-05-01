package cli;

import java.util.List;

public interface Command {
    void execute(List<String> arguments);
}
