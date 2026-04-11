package shell.command;

// is it reasonable?
public class NotACommand implements Command {
    private final String name;

    public NotACommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(String... args) {
        return new CommandResult(String.format("%s: not a command%n", this.name));
    }
}
