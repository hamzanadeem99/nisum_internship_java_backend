public class Main {
    public static void main(String[] args) {
        // UpCasting: Parent reference to child objects
        Shape myCircle = new Circle(7.0);
        Shape myRect = new Rectangle(8.0, 3.0);
        Shape myTri = new Triangle(2.0, 9.0);

        // Run-Time Polymorphism: The JVM decides which method to call at execution
        myCircle.calculateArea();
        myRect.calculateArea();
        myTri.calculateArea();
    }
}
