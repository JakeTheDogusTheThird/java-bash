package shell.command;

public interface Command {
    CommandResult execute(String... args);
}
