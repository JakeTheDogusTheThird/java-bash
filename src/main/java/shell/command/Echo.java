package shell.command;

public class Echo implements Command {
    @Override
    public CommandResult execute(String... args) {
        if (args.length == 0) {
            return new CommandResult("");
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(" ");
        }
        sb.replace(sb.length() - 1, sb.length(), "\n");
        return new CommandResult(sb.toString());
    }
}
