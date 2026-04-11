package shell.context;

public class ContextFactory {
    private static final String OS = System.getProperty("os.name");

    public static ShellContext getContext() {
        return OS.contains("win")
                ? new WindowsShellContext()
                : new UnixShellContext();
    }
}
