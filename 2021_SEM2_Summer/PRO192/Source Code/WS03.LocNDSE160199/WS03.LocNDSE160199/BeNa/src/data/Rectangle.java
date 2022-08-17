
package data;

public class Rectangle {
    private String color;
    private double width;
    private double height;
    private String owner;

    public Rectangle() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getOwner() {
        return owner;    
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("|Rectangle |%-15s|%-6.2f|%-6.2f|%-8s|\n", owner, width, height, color);
    }

    public void showInfo() {
        System.out.printf("|Rectangle |%-15s|%-6.2f|%-6.2f|%-8s|\n", owner, width, height, color);
    }
    
}
