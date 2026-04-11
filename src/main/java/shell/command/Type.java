package shell.command;

import shell.context.ShellContext;

public class Type implements Command {
    private final ShellContext context;

    public Type(ShellContext context) {
        this.context = context;
    }

    @Override
    public CommandResult execute(String... args) {
        if (args.length == 0) {
            return new CommandResult("");
        }

        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (context.isBuiltIn(arg)) {
                sb.append(String.format("%s is a shell built in%n", arg));
                continue;
            }
            if (context.isExternal(arg)) {
                sb.append(String.format("%s is %s%n", arg, context.getExternalPath(arg)));
            } else {
                sb.append(String.format("%s is not a shell built in%n", arg));
            }
        }
        return new CommandResult(sb.toString());
    }
}
