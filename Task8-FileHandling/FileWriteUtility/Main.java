import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String filePath = "/Users/hanadeem/Desktop/cmd files/Output.txt";

        System.out.println("Enter the text that you want to save to the file: ");
        String data = sc.nextLine();

        // Try with resources to ensure the file closes automatically
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write(data);
            System.out.println("Success");
            System.out.println("Data saved at: " + new File(filePath).getAbsolutePath());
        } catch (IOException e) {
            System.err.println("An error occured while writing the file.");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
