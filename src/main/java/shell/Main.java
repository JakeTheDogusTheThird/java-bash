package shell;

import shell.context.ContextFactory;
import shell.context.ShellContext;

import java.util.Scanner;

//Read, Eval, Print, Loop

public class Main {
    public static void main(String[] args) {
        ShellContext context = ContextFactory.getContext();
        Scanner sc = new Scanner(System.in);
        Shell shell = new Shell(context, sc);
        shell.run();

        System.out.println(System.getProperty("os.name"));
    }
}
