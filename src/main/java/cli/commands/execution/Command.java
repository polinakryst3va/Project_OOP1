package main.java.cli.commands.execution;

import java.io.IOException;
import java.util.List;

public interface Command {
    void execute(List<String> arguments) throws IOException;
}
