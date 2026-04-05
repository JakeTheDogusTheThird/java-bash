package shell;

public class Type implements Command {
    @Override
    public CommandResult execute(String... args) {
        if (args.length == 1) {
            return new CommandResult("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (CommandFactory.isBuiltIn(args[i])) {
                sb.append(String.format("%s is a shell built in%n", args[i]));
            } else {
                sb.append(String.format("%s is not a shell built in%n", args[i]));
            }
        }
        return new CommandResult(sb.toString());
    }
}
