package shell;

public interface Command {
    CommandResult execute(String... args);
}
