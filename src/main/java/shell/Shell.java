package shell;


import shell.command.Command;
import shell.command.CommandResult;
import shell.context.ShellContext;

import java.util.Scanner;

public class Shell implements Runnable {
    private final ShellContext shellContext;
    private final Scanner in;

    public Shell(ShellContext shellContext, Scanner in) {
        this.shellContext = shellContext;
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("$ ");
            Expression expression = read();
            CommandResult result = evaluate(expression);

            if (result.shouldExit()) {
                break;
            }

            System.out.print(result.getStdout());
        }
    }

    public Expression read() {
        String line = in.nextLine();
        return new Expression(line);
    }

    public CommandResult evaluate(Expression expression) {
        Command command = this.shellContext.getCommand(expression.command());
        return command.execute(expression.arguments());
    }
}
