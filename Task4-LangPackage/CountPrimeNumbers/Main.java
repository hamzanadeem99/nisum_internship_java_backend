import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Positive Number: ");
        int limit = scanner.nextInt();

        int count = 0;
        for (int i = 2; i < limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        System.out.println("Number of prime numbers less than " + limit + ": " + count);
    }

    // Method to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        // We only need to checkup to the square root of n for efficiency
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;  // Found a divisor, so not prime number
            }
        }
        return true; // No divisor found, it is prime number
    }
}
