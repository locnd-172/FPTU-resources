
package data;

public class Circle {
    private String color;
    private double radius;
    private String owner;

    public Circle() {
    }
   
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("|Circle    |%-15s|%-13.2f|%-8s|\n", owner, radius, color);
    }
    
    public void showInfo() {
        System.out.printf("|Circle    |%-15s|%-13.2f|%-8s|\n", owner, radius, color);
    }
    
}
