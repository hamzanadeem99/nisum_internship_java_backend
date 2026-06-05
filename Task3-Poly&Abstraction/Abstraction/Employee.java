class Employee implements Person {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void work() {
        System.out.println(name + " is working as an Employee (ID: " + id + ").");
    }

    @Override
    public void displayIdentity() {
        System.out.println("Identity: " + name);
    }

    public String getName() {
        return name;
    }
}
