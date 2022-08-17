
package data;

public class Account {
    private long accountNumber;
    private String name;
    private String idNumber;
    private String phone;
    private int balance;

    public Account(long accountNumber, String name, String idNumber, String phone, int balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.idNumber = idNumber;
        this.phone = phone;
        this.balance = balance;
    }

    public void printAccountInfo() {
        System.out.println("Acc. number: " + this.accountNumber);
        System.out.println("Name: " + this.name);
        System.out.println("ID number: " + this.idNumber);
        System.out.println("Phone: " + this.phone);
        System.out.println("Balance: " + this.balance + " VND");
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }    
}
