package shell;


import java.util.Scanner;

public class Shell implements Runnable {
    private final CommandFactory commandFactory;
    private final Scanner in;

    public Shell(CommandFactory commandFactory, Scanner in) {
        this.commandFactory = commandFactory;
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
        Command command = this.commandFactory.getCommand(expression.command());
        return command.execute(expression.arguments());
    }
}
