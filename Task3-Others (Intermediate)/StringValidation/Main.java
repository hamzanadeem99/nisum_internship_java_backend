import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        if (input.matches("^[A-Za-z]+$")) {
            System.out.println("Valid String");
        } else {
            System.out.println("Invalid String");
        }
    }
}
