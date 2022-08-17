package data;

public class Triangle {
    protected double a;
    protected double b;
    protected double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Triangle{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }

    public double getArea() {
        double p = (a + b + c) / 2;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;

    }

    public void printInfo() {
        System.out.printf("|TRIANGLE       | %-5.2f | %-5.2f | %-5.2f | Area: %-7.2f|\n", a, b, c, getArea());
    }
}
