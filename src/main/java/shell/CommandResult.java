package shell;

public class CommandResult {
    private final String stdout;
    private final String stderr;
    private final boolean shouldExit;

    public CommandResult(String stdout) {
        this(stdout, "", false);
    }

    public CommandResult(String stdout, String stderr) {
        this(stdout, stderr, false);
    }

    public CommandResult(boolean shouldExit) {
        this(null, null, shouldExit);
    }

    public CommandResult(String stdout, String stderr, boolean exitFlag) {
        this.stdout = stdout;
        this.stderr = stderr;
        this.shouldExit = exitFlag;
    }

    public String getStdout() {
        return this.stdout;
    }

    public String getStderr() {
        return this.stderr;
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }
}
