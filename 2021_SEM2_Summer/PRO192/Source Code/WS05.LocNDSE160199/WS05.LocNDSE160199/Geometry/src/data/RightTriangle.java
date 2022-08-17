package data;

public class RightTriangle extends Triangle {

    public RightTriangle(double a, double b) {
        super(a, b, Math.sqrt(a * a + b * b));
    }
    
    @Override
    public void printInfo() {
        System.out.printf("|RIGHT TRIANGLE | %-5.2f | %-5.2f | %-5.2f | Area: %-7.2f|\n", a, b, c, getArea());
    }

}
