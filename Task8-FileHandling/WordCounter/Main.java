import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        // Get the file path and target word from user
        System.out.print("Enter the full path of the text file: ");
        String filePath = consoleScanner.nextLine();

        System.out.print("Enter the word to count (case-insensitive): ");
        String targetWord = consoleScanner.nextLine().trim();

        File file = new File(filePath);

        // Validation
        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: The file does not exist at the specified path.");
            return;
        }

        int count = 0;

        // Process the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");

                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }

            System.out.println("\nAnalysis Complete:");
            System.out.println("The word '" + targetWord + "' appeared " + count + " tinmes.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } finally {
            consoleScanner.close();
        }
    }
}
