public class Main {
    
    public static void main(String[] args) {
        long milliseconds = System.currentTimeMillis();
        long seconds = milliseconds / 1000;

        System.out.println("Milliseconds since 1970: " + milliseconds);
        System.out.println("Seconds since 1970: " + seconds);
    }
}
