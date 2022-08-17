package list;

import data.Customer;
import java.io.IOException;
import java.util.Scanner;
import singlelinkedlist.SLL;
import util.FileIO;
import util.Validation;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class CustomerList  {
    public SLL<Customer> cusList = new SLL();
    private Scanner sc = new Scanner(System.in);
    private int halt = 0;
    private String header = String.format("%-3s| %-5s| %-15s| %-10s\n",
                                            "No", "CODE", "NAME", "PHONE");
    
    public void emptyNotify() {
        System.out.println("The customer list is empty.");
        Validation.getEnter("ENTER to back to main menu...");
    }
    
    public void loadData() {
        System.out.println("_________LOAD CUSTOMER DATA_________");
        String filename = Validation.inputString("File name: ", "Can't contain special character!");
        filename = filename.trim();
        filename += ".txt";
        try {
            cusList = FileIO.loadCustomer(filename);
            System.out.println("LOAD SUCCESSFUL!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("LOAD FAILED!");
        }
        
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void addNewCustomerFunc() {
        System.out.println("_____________________ADD NEW CUSTOMER_____________________");
        System.out.println("Code includes 1 uppercase letters + 2 digits (e.g: C01)");
        halt = 0;
        do {
            if (addNewCustomer() == true) {
                System.out.println("ADD SUCCESSFUL!");
            } else {
                System.err.println("ADD FAILED!");
            }
            halt = (Validation.confirmContinue("\nContinue adding? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_________________________________");
        } while (halt == 0);
    }
    
    public boolean addNewCustomer() {
        try {
            String ccode, name;
            String phone;

            int pos;

            do {
                ccode = Validation.inputCode("Customer code: ", "Error: Wrong code format!\nCode includes 1 uppercase letters + 2 digits (e.g: C01)!");
                pos = searchCustomerByCode(ccode);
                if (pos != -1) {
                    System.err.println("This code already exist! Enter another one!");
                }
            } while (pos != -1);

            name = Validation.inputName("Name: ", "Eror: Invalid name! Can't contain special characters");
            phone = Validation.inputPhone("Phone: ");
                
            cusList.addLast(new Customer(ccode, name, phone));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int searchCustomerByCode(String scode) throws Exception {
        for (int i = 0; i < cusList.size(); ++i) {
            if (cusList.get(i).getCode().equalsIgnoreCase(scode)) {
                return i;
            }
        }
        return -1;
    }
    
    public void searchCustomerByCodeFunc() throws Exception {
        if (cusList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________SEARCH A CUSTOMER BY NAME___________");
        halt = 0;
        do {
            searchCustomerByCode();
            halt = (Validation.confirmContinue("Continue searching? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_________________________________");
        } while (halt == 0);
    }
    
    public void searchCustomerByCode() throws Exception {
        String findCode = Validation.inputCode("Customer code: ", "Please input valid code (e.g: C01)");

        int found = 0;
        for (int i = 0; i < cusList.size(); ++i) {
            String curName = cusList.get(i).getCode();
            if (curName.equalsIgnoreCase(findCode) == true) {
                cusList.get(i).showInfo();
                found = 1;
            }
        }
        if (found == 0) {
            System.err.println("NOT FOUND! The customer with ID " + findCode + " does not exist!");
        }
    }
    
    public void removeProducByCode() throws Exception {
        if (cusList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________REMOVE A CUSTOMER BY CODE___________");
        String ccode;
        int pos;

        ccode = Validation.inputCode("Customer code: ", "Please input valid code (e.g: C01)");
        pos = searchCustomerByCode(ccode);

        if (pos == -1) {
            System.err.println("NOT FOUND! Customer with code " + ccode + " does not exist.");
        } else {
            System.out.print("Customer to be removed: ");
            cusList.get(pos).showInfo();

            if (Validation.confirmContinue("\nAre you sure? (y/n)... ", "Invalid input") == 1) {
                cusList.removePos(pos);
                System.out.println("REMOVE SUCCESSFUL!");
            } else {
                System.out.println("REMOVE FAILED!");
            }
        }
        Validation.getEnter("ENTER to back to main menu...");
    }
    
    public void printCustomerList() throws Exception {
        if (cusList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("________PRINT CUSTOMER LIST________");
        System.out.print(header);
        for (int i = 0; i < cusList.size(); ++i) {
            System.out.printf("%02d | ", i + 1);
            cusList.get(i).showInfo();
        }
        Validation.getEnter("ENTER to back to previous...");
    }

    public void saveToFile() throws Exception {
        System.out.println("________SAVE CUSTOMER LIST TO FILE________");
        String filename = Validation.inputString("File name: ", "Can't contain special chars");
        filename = filename.trim();
        filename += ".txt";
        try {
            if (cusList.isEmpty()) {
                System.out.println("The product is empty. The program will save an empty list!");
                FileIO.saveCustomer(filename, "", cusList);
            } else {
                FileIO.saveCustomer(filename, header, cusList);
                System.out.println("SAVE SUCCESSFUL!");
            }
        } catch (IOException e) {
            System.out.println("SAVE FAILED!");
        }
        Validation.getEnter("ENTER to back to previous...");
    }
}

