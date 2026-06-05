public class Main {

    public static void main(String[] args) {
        try {
            String testInput = null;
            System.out.println("Validating String");

            // This will trigger the exception
            validateInput(testInput);

            System.out.println("String is valid: " + testInput);
        } catch (NullStringException e) {
            // Catching and handling the custom exception
            System.err.println("Caught Custom Error: " + e.getMessage());
        }
    }

    // Checks if a string is null and throw a custom exception if it is
    public static void validateInput(String str) {
        if (str == null) {
            throw new NullStringException("The provided String is NULL. Please provide a valid value.");
        }
    }
}
