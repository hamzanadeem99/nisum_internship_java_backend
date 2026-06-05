public class Main {
    public static void main(String[] args) {
        // Testing Inheritance
        Programmer dev =  new Programmer();
        dev.work();
        dev.code();

        // Testing Aggregation
        Professor prof = new Professor("Dr. Ali Akbar");
        Department cs = new Department(prof);

        // Testing Association
        Bank meznb = new Bank("Meezan Bank");
        Customer hamza = new Customer("Hamza");
        hamza.browseBank(meznb);
    }
}
