import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        System.out.print("Enter the full path of the text file: ");
        String filePath = consoleScanner.nextLine().trim();

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: The file doesn't exist.");
            return;
        }

        // HashMap to store word frequency
        Map<String, Integer> wordCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split into words and clean them up
                String[] words = line.split("\\W+");

                for (String word : words) {
                    if (word.isEmpty()) continue;

                    String cleanWord = word.toLowerCase(); // Ensure case-insensitivity

                    // Update the frequency map
                    // getOrDefault is a clean, modern way to handle new vs existing keys
                    wordCounts.put(cleanWord, wordCounts.getOrDefault(cleanWord, 0) + 1);
                }
            }

            System.out.println("Word Frequency Analysis");

            for (Map.Entry<String, Integer> entry: wordCounts.entrySet()) {
                System.out.printf("%-15s | %d%n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } finally {
            consoleScanner.close();
        }
    }
}
