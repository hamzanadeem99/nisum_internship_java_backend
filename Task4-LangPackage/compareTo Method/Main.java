public class Main {
    public static void main(String[] args) {
        String string1 = "Apple";
        String string2 = "Banana";
        String string3 = "Apple";
        String string4 = "apple";  // Lowercase 'a' has a higher unicode value than 'A'
        System.out.println("Comparison");

        // Comparing equal strings
        System.out.println("string1 vs string3 (Apple vs Apple):  " + string1.compareTo(string3));

        // Comparing Apple to Banana ('A' comes before 'B')
        System.out.println("string1 vs string2 (Apple vs Banana): " + string1.compareTo(string2));

        // Comparing Banana to Apple ('B' comes after 'A')
        System.out.println("string2 vs string1 (Banana vs Apple): " + string2.compareTo(string1));

        // Case Sensitivity: 'A' (65) vs 'a' (97)
        System.out.println("string1 vs string4 (Apple vs apple): " + string1.compareTo(string4));

        // If you want to ignore case
        System.out.println("string1 vs string4 (Ignoring Case): " + string1.compareToIgnoreCase(string4));
    }
}
