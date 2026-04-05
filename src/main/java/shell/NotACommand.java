package shell;

// is it reasonable?
public class NotACommand implements Command {
    @Override
    public CommandResult execute(String... args) {
        return new CommandResult(String.format("%s: not a command%n", args[0]));
    }
}
