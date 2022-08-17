package data;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Order {
    private String pcode;
    private String ccode;
    private int quantity;

    public Order(String pcode, String ccode, int quantity) {
        this.pcode = pcode;
        this.ccode = ccode;
        this.quantity = quantity;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-6s| %-6s| %-9d\n", pcode, ccode, quantity);
    }
    
    public void showInfo() {
        System.out.print(this.toString());
    }
}
