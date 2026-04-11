package shell.context;

import shell.command.Command;

public interface ShellContext {
    boolean isBuiltIn(String commandName);
    boolean isExternal(String commandName);
    Command getCommand(String commandName);
    String getExternalPath(String commandName);
}
