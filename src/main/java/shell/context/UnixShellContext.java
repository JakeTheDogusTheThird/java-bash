package shell.context;

import java.io.File;

public class UnixShellContext
        extends AbstractShellContext implements ShellContext {
    @Override
    public boolean isExecutable(String directory, String commandName) {
        File file = new File(directory, commandName);
        if (file.exists() && file.canExecute()) {
            externalCache.put(commandName, file.getAbsolutePath());
            return true;
        }
        return false;
    }
}
