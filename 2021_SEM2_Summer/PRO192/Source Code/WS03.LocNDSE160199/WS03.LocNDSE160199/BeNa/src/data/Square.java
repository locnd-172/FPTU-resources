package data;

public class Square {
    private String color;
    private double edgeLength;
    private String owner;

    public Square() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(double edgeLength) {
        this.edgeLength = edgeLength;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("|Square    |%-15s|%-13.2f|%-8s|\n", owner, edgeLength, color);
    }

    public void showInfo() {
        System.out.printf("|Square    |%-15s|%-13.2f|%-8s|\n", owner, edgeLength, color);
    }
    
}
