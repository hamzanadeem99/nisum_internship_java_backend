class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    // Getter and setter
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        double area = Math.PI * radius * radius;
        System.out.println("Circle Area: " + String.format("%.2f", area));
    }
}
