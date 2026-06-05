import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get source and destination paths
        System.out.print("Enter the source file path: ");
        String sourcePath = scanner.nextLine();

        System.out.print("Enter the destination file path: ");
        String destPath = scanner.nextLine();

        File sourceFile = new File(sourcePath);

        // Validation
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("Error: Source file doesn't exist.");
            return;
        }

        // Perform the Copy (Manual Read/Write Approach)
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line); // Keeps the original line breaks
            }

            System.out.println("File contents copied successfuly to: " + destPath);
        } catch (IOException e) {
            System.out.println("An errror occured during the copy process.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
