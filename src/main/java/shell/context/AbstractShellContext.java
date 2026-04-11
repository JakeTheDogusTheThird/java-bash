package shell.context;

import shell.command.Command;
import shell.command.Echo;
import shell.command.Exit;
import shell.command.NotACommand;
import shell.command.Type;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractShellContext implements ShellContext {
    private final Map<String, Command> BUILTINS = new HashMap<>();

    protected final Map<String, String> externalCache = new HashMap<>();
    protected final static String PATH = System.getenv("PATH");
    protected final static String PATH_SEPARATOR = File.pathSeparator;

    AbstractShellContext() {
        init();
    }

    private void init() {
        BUILTINS.put("exit", new Exit());
        BUILTINS.put("echo", new Echo());
        BUILTINS.put("type", new Type(this));
    }

    @Override
    public boolean isBuiltIn(String commandName) {
        return BUILTINS.containsKey(commandName);
    }

    @Override
    public boolean isExternal(final String commandName) {
        String[] pathDirectories = PATH.split(PATH_SEPARATOR);
        for (String directoryPath : pathDirectories) {
            File directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                continue;
            }
            if (isExecutable(directoryPath, commandName)) {
                return true;
            }
        }
        return false;
    }

    abstract boolean isExecutable(String directory, String commandName);

    @Override
    public Command getCommand(String commandName) {
        if (isBuiltIn(commandName)) {
            return BUILTINS.get(commandName);
        }
        if (isExternal(commandName)) {
            return new NotACommand(commandName);
        }
        return new NotACommand(commandName);
    }

    @Override
    public String getExternalPath(String commandName) {
        return externalCache.get(commandName);
    }
}
