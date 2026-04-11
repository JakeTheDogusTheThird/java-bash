package shell.context;

import shell.command.Command;
import shell.command.Echo;
import shell.command.Exit;
import shell.command.NotACommand;
import shell.command.Type;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class AbstractShellContext implements ShellContext {
    private final Map<String, Command> builtins = new HashMap<>();

    protected final Map<String, String> externalCache = new HashMap<>();
    protected final static String PATH = System.getenv("PATH");
    protected final static String PATH_SEPARATOR = File.pathSeparator;
    protected String[] pathDirectories = PATH.split(PATH_SEPARATOR);

    AbstractShellContext() {
        init();
    }

    private void init() {
        builtins.put("exit", new Exit());
        builtins.put("echo", new Echo());
        builtins.put("type", new Type(this));
    }

    @Override
    public boolean isBuiltIn(String commandName) {
        return builtins.containsKey(commandName);
    }

    @Override
    public Optional<String> resolveExternal(final String commandName) {
        if (externalCache.containsKey(commandName)) {
            return Optional.of(externalCache.get(commandName));
        }
        for (String directoryPath : pathDirectories) {
            File directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                continue;
            }
            if (isExecutable(directoryPath, commandName)) {
                String fullPath = directoryPath + File.separator + commandName;
                externalCache.put(commandName, fullPath);
                return Optional.of(fullPath);
            }
        }
        return Optional.empty();
    }

    abstract boolean isExecutable(String directory, String commandName);

    @Override
    public Command getCommand(String commandName) {
        if (isBuiltIn(commandName)) {
            return builtins.get(commandName);
        }
//        if (resolveExternal(commandName)) {
//            return new NotACommand(commandName);
//        }
        return new NotACommand(commandName);
    }

    @Override
    public boolean isExternal(String commandName) {
        return resolveExternal(commandName).isPresent();
    }
}
