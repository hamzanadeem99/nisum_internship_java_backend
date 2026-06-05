class DatabaseConnection {

    // Single Instance, Stored Inside the class itself
    private static DatabaseConnection instance = null;

    // Private Singleton Constructor
    // This is what prevents second object from being created
    private DatabaseConnection() {
        System.out.println("DatabaseConnection object created.");
    }

    // public static method to get the ONE instance
    // this is the only door to get the object
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            // first time, no object exists yet, create it
            instance = new DatabaseConnection();
        }
        // object already exist, return same one (second, third time etc.)
        return instance;
    }

    // simple method to confirm which object is created
    public void showStatus() {
        System.out.println("Connected to database. Object: " + this);
    }
}
