public class Main {

    public static void main(String[] args) {

        System.out.println("Main Thread Started.");

        // Create Thread1 and Thread2
        CountThread thread1 = new CountThread("Thread1", 5);
        CountThread thread2 = new CountThread("Thread2", 3);

        // Start both threads
        thread1.start();
        thread2.start();

        // Main thread waits for Thread1 and Thread2 to finish
        // join() makes main thread wait, without using sleep()
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Main Thread was interrupted: " + e.getMessage());
        }

        System.out.println("Main Thread ended (After Thread1 and Thread2 finished).");
    }
}
