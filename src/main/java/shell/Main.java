package shell;

import java.util.Scanner;


//Read, Eval, Print, Loop

public class Main {
    public static void main(String[] args) {
        CommandFactory cf = new CommandFactory();
        Scanner sc = new Scanner(System.in);
        Shell shell = new Shell(cf, sc);
        shell.run();
    }
}
