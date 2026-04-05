import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
         System.out.print("$ ");

         Scanner in = new Scanner(System.in);
         String command = in.nextLine();
         System.out.format("%s: command not found", command);
    }
}
