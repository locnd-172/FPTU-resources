package data;

import java.util.Comparator;

public class Watch {
    private String id;
    private String name;
    private String brandName;
    private String gender;
    private double size;
    private double price;

    public Watch(String id, String name, String brandName, String gender, double size, double price) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.gender = gender;
        this.size = size;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Watch{" + "id=" + id + ", name=" + name + ", brandName=" + brandName + ", gender=" + gender + ", size=" + size + ", price=" + price + '}';
    }
    
    public void showInfo() {
        System.out.printf("|%-11s|%-15s|%-8s|%-6s|%4.2f mm|%12.1f|\n",
                            id, name, brandName, gender, size, price);
    }
}
