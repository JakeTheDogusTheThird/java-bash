package shell;

public class Exit implements Command {
    @Override
    public CommandResult execute(String... args) {
        return new CommandResult(true);
    }
}
