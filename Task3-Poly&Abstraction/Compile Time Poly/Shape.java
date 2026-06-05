class Shape {
    // Circle Area
    public void calculateArea(double radius) {
        double area = Math.PI * radius * radius;
        System.out.println("Circle Area: " + String.format("%.2f", area));
    }

    // Rectangle Area
    public void calculateArea(double length, double width) {
        double area = length * width;
        System.out.println("Rectangle Area: " + area);
    }

    // Triangle Area
    public void calculateArea(float base, float height) {
        double area = 0.5 * base * height;
        System.out.println("Triangle Area: " + area);
    }
}
