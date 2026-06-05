public class Main {
    public static void main(String[] args) {
        String input = "Success Requires Patience";

        // Split the string by spaces to get each word
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                // Capitalize first letter + remaining letters
                String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1);

                result.append(capitalized).append(" ");
            }
        }

        System.out.println("Original:  "  + input);
        System.out.println("Capitalized: " + result.toString().trim());
    }
}
