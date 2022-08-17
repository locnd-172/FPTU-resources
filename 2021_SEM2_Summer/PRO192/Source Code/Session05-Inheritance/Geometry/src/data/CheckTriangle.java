
package data;

public class CheckTriangle {
    protected double a;
    protected double b;
    protected double c;

    public CheckTriangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

   public boolean isTriangle() {
       return (this.a + this.b > this.c && this.b + this.c > this.a && this.a + this.c > this.b);
   }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
   
    public void printInfo() {
        System.out.printf("|FIGURE| %-5.2f | %-5.2f | %-5.2f |", a, b, c);
    }

}
