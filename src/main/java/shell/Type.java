package shell;

import java.io.File;
import java.util.Optional;

public class Type implements Command {
    private static final String SEPARATOR = File.pathSeparator;
    private static final String PATH = System.getenv("PATH");
    private static final String[] PATH_EXT = System.getenv("PATHEXT").split(SEPARATOR);

    @Override
    public CommandResult execute(String... args) {
        if (args.length == 0) {
            return new CommandResult("");
        }

        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (CommandFactory.isBuiltIn(arg)) {
                sb.append(String.format("%s is a shell built in%n", arg));
                continue;
            }

            Optional<String> commandPath = searchCommand(arg);
            if (commandPath.isPresent()) {
                sb.append(String.format("%s is %s%n", arg, commandPath.get()));
            } else {
                sb.append(String.format("%s is not a shell built in%n", arg));
            }
        }
        return new CommandResult(sb.toString());
    }

    private Optional<String> searchCommand(String command) {
        // windows
        String[] pathDirectories = PATH.split(SEPARATOR);
        for (String directoryPath : pathDirectories) {
            File directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                continue;
            }
            for (String extension : PATH_EXT) {
                File file = new File(directory, command + extension);
                if (file.exists() && file.canExecute()) {
                    return Optional.of(file.getAbsolutePath());
                }
            }
        }
        return Optional.empty();
    }
}
