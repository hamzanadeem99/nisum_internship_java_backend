public class Main {
    public static void main(String[] args) {
        Shape myShape = new Shape();

        // The compiler decides which method to call based on the arguments passed.
        myShape.calculateArea(5.9);
        myShape.calculateArea(10.8, 7.0);
        myShape.calculateArea(4.0f, 6.0f);
    }
}
