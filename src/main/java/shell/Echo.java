package shell;

public class Echo implements Command {
    @Override
    public CommandResult execute(String... args) {
        if (args.length == 1) {
            return new CommandResult("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }
        sb.replace(sb.length() - 1, sb.length(), "\n");
        return new CommandResult(sb.toString());
    }
}
