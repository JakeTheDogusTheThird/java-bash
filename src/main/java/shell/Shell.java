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
            String[] expression = read();
            CommandResult result = evaluate(expression);

            if (result.shouldExit()) {
                break;
            }

            System.out.print(result.getStdout());
        }
    }

    public String[] read() {
        System.out.print("$ ");
        String line = in.nextLine();
        return line.split("( +)");
    }

    public CommandResult evaluate(String[] expression) {
        Command command = this.commandFactory.getCommand(expression[0]);
        return command.execute(expression);
    }
}
