package shell.context;

import java.io.File;

public class WindowsShellContext
        extends AbstractShellContext implements ShellContext {
    private static final String[] PATH_EXT = System.getenv("PATHEXT").split(PATH_SEPARATOR);

    boolean isExecutable(String directory, String commandName) {
        for (String extension : PATH_EXT) {
            File file = new File(directory, commandName + extension);
            if (file.exists() && file.canExecute()) {
                externalCache.put(commandName, file.getAbsolutePath());
                return true;
            }
        }
        return false;
    }
}
