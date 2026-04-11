package shell.context;

import shell.command.Command;

import java.util.Optional;

public interface ShellContext {
    boolean isBuiltIn(String commandName);
    boolean isExternal(String commandName);
    Optional<String> resolveExternal(String commandName);
    Command getCommand(String commandName);
}
