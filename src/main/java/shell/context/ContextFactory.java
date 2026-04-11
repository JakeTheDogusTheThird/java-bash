package shell.context;

public class ContextFactory {
    public static ShellContext getContext() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win")
                ? new WindowsShellContext()
                : new UnixShellContext();
    }
}
