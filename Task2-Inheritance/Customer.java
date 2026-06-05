class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }

    // Association: Customer uses a bank
    void browseBank(Bank bank) {
        System.out.println(name + " is visiting " + bank.getName());
    }
}
