import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        // Add 1 month
        LocalDateTime plusMonths = now.plusMonths(1);

        // Subtract 15 days
        LocalDateTime minusDays = now.minusDays(15);

        // Format (EEE MMM dd HH:mm:ss yyyy)
        DateTimeFormatter firstFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy");

        // Format (yyyy MMM dd HH:mm:ss)
        DateTimeFormatter secondFormat = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss");

        System.out.println("Current Date: " + now);
        System.out.println("After adding 1 month: " + plusMonths);
        System.out.println("After substracting 15 days: " + minusDays);

        System.out.println("\nFirst Format: " + now.format(firstFormat));
        System.out.println("Second Format: " + now.format(secondFormat));
    }
}
