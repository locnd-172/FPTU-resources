package data;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Product {
    private String pcode;
    private String productName;
    private int quantity;
    private int saled;
    private double price;

    public Product(String code, String name, int quantity, int saled, double price) {
        this.pcode = code;
        this.productName = name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

    public String getCode() {
        return pcode;
    }

    public void setCode(String code) {
        this.pcode = code;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaled() {
        return saled;
    }

    public void setSaled(int saled) {
        this.saled = saled;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getValue(int saled, double price) {
        return (double)(saled * price);
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-15s| %-9d| %-6d| %-6.2f| %-6.2f\n", 
                pcode, productName, quantity, saled, price, getValue(saled, price));
    }

    public void showInfo() {
        System.out.print(this.toString());
    }
    
}
