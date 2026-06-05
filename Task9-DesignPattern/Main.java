public class Main {

    public static void main(String[] args) {

        System.out.println("Singleton Design Pattern");

        // First call, object get created
        System.out.println("\nGetting First Instance");
        DatabaseConnection firstConnection = DatabaseConnection.getInstance();
        firstConnection.showStatus();

        // Second call, same object returned, constructor doesn't run again
        System.out.println("\nGetting Second Instance");
        DatabaseConnection secondConnection = DatabaseConnection.getInstance();
        secondConnection.showStatus();

        // Third call
        System.out.println("\nGetting Third Instance");
        DatabaseConnection thirdConnection = DatabaseConnection.getInstance();
        thirdConnection.showStatus();

        // Proof by printing objects, they show the same memory address
        System.out.println("\nProof by printing objects");
        System.out.println("firstConnection: " + firstConnection);
        System.out.println("secondConnection: " + secondConnection);
        System.out.println("thirdConnection: " + thirdConnection);

        // Proof by comparison operator
        System.out.println("\nProof by comparison operator");
        System.out.println("firstConnection == secondConnection: " + (firstConnection == secondConnection));
        System.out.println("secondConnection == ThirdConnection: " + (secondConnection == thirdConnection));
        System.out.println("firstConnection == ThirdConnection: " + (firstConnection == thirdConnection));
    }
}
