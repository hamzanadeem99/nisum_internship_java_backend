import java.io.File;

public class Main {

    public static void main(String[] args) {

        // Folder path
        String folderPath = "/Users/hanadeem/Desktop/cmd files";

        // Create file object
        File folder = new File(folderPath);

        // Check if folder exists
        if (folder.exists() && folder.isDirectory()) {

            // Get list of files
            File[] files = folder.listFiles();

            System.out.println("Files in the folder:");

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Invalid folder path.");
        }
    }
}
