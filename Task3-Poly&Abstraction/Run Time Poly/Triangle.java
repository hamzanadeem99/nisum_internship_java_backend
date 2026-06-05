class Triangle extends Shape {

    double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void calculateArea() {
        double area = 0.5 * base * height;
        System.out.println("Triangle Area: " + area);
    }
}
