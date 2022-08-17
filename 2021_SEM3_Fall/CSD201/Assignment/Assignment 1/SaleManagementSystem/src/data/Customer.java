package data;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Customer {
    private String ccode;
    private String customerName;
    private String phone;

    public Customer(String code, String name, String phone) {
        this.ccode = code;
        this.customerName = name;
        this.phone = phone;
    }

    public String getCode() {
        return ccode;
    }

    public void setCode(String code) {
        this.ccode = code;
    }

    public String getName() {
        return customerName;
    }

    public void setName(String name) {
        this.customerName = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-15s| %-10s\n", ccode, customerName, phone);
    }
    
    public void showInfo() {
        System.out.print(this.toString());
    }
}
