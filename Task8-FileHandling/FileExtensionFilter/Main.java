import java.io.File;

public class Main {
    public static void main(String[] args) {

        // Folder Path
        String folderPath = "/Users/hanadeem/Desktop/cmd files";

        // File extension to search
        String extension = ".xlsx";

        File folder = new File(folderPath);

        // Check for valid directory

        if (folder.exists() && folder.isDirectory()) {

            File[] files = folder.listFiles();

            System.out.println("Files with extension " + extension + ":");

            if (files != null) {
                for (File file : files) {

                    // Check if the file matches extension
                    if (file.isFile() && file.getName().toLowerCase().endsWith(extension)) {
                        System.out.println(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Invalid folder path.");
        }
    }
}
