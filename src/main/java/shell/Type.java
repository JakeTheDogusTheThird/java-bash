package shell;

import java.io.File;
import java.util.Optional;

public class Type implements Command {
    private static final String PATH = System.getenv("PATH");
    private static final String[] PATH_EXT = System.getenv("PATHEXT").split(";");

    @Override
    public CommandResult execute(String... args) {
        if (args.length == 1) {
            return new CommandResult("");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (CommandFactory.isBuiltIn(args[i])) {
                sb.append(String.format("%s is a shell built in%n", args[i]));
            }

            Optional<String> commandPath = searchCommand(args[i]);
            if (commandPath.isPresent()) {
                sb.append(String.format("%s is %s%n", args[i], commandPath.get()));
            } else {
                sb.append(String.format("%s is not a shell built in%n", args[i]));
            }
        }
        return new CommandResult(sb.toString());
    }

    private Optional<String> searchCommand(String command) {
        // windows
        String[] pathDirectories = PATH.split(";");
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
