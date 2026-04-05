import java.util.Scanner;


//Read, Eval, Print, Loop

public class Main {
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      while (true) {
        System.out.print("$ ");
        String command = in.nextLine();
        System.out.format("%s: command not found%n", command);
      }
    }
}
