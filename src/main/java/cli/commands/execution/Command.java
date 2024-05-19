package main.java.cli.commands.execution;

import java.util.List;

public interface Command {
    void execute(List<String> arguments);
}
