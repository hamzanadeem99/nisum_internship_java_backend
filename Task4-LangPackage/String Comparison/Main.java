public class Main {
    public static void main(String[] args) {
        // String Literals (Stored in the String Pool)
        String string1 = "Java";
        String string2 = "Java";

        // String Objects (Stored in the Heap)
        String string3 = new String("Java");
        String string4 = new String("Java");

        System.out.println("Comparison Results");

        // Comparing Literals (string1 vs string2)
        // Both point to the same memory location in the pool
        System.out.println("string1 == string2: " + (string1 == string2));          // true
        System.out.println("string1.equals(string2): " + string1.equals(string2));  // true

        // Comparing Literal vs Object (string1 vs string3)
        // Different memory locations (Pool vs Heap)
        System.out.println("string1 == string3: " + (string1 == string3));         // false
        System.out.println("string1.equals(string3): " + string1.equals(string3)); // true

        // Comparing two objects (string3 vs string4)
        // Different memory locations in the Heap
        System.out.println("string3 == string4: " + (string3 == string4));          // false
        System.out.println("string3.equals(string4):  " + string3.equals(string4)); // true
    }
}
