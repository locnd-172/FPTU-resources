package list;

import data.Product;
import java.io.IOException;
import java.util.Scanner;
import singlelinkedlist.SLL;
import util.FileIO;
import util.Validation;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class ProductList {

    public SLL<Product> prodList = new SLL<>();
    private Scanner sc = new Scanner(System.in);
    private int halt = 0;
    private String header = String.format("%-3s| %-5s| %-15s| %-9s| %-6s| %-6s| %-10s\n",
            "No", "CODE", "NAME", "QUANTITY", "SOLD", "PRICE", "TOT. VALUE");

    public void emptyNotify() {
        System.out.println("The product list is empty.");
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void loadData() {
        System.out.println("_______________LOAD PRODUCT DATA_______________");
        String filename = Validation.inputString("File name: ", "Can't contain special character!");
        filename = filename.trim();
        filename += ".txt";
        try {
            prodList = FileIO.loadProduct(filename);
            System.out.println("LOAD SUCCESSFUL!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("LOAD FAILED!");
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void addNewProductFunc() {
        System.out.println("_____________________ADD NEW PRODUCT_____________________");
        System.out.println("Code includes 1 uppercase letters + 2 digits (e.g: P01)");
        halt = 0;
        do {
            if (addNewProduct(-1) == true) {
                System.out.println("ADD SUCCESSFUL!");
            } else {
                System.out.println("ADD FAILED!");
            }
            halt = (Validation.confirmContinue("\nContinue adding? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_________________________________");
        } while (halt == 0);
    }
    
    public boolean addNewProduct(int K) {
        try {
            String pcode, name;
            int quantity, sold;
            double price;

            int pos;

            do {
                pcode = Validation.inputCode("Product code: ", "Error: Wrong code format!\nCode includes 1 uppercase letters + 2 digits (e.g: P01)!");
                pos = searchProductByCode(pcode);
                if (pos != -1) {
                    System.out.println("This code already exist! Enter another one!");
                }
            } while (pos != -1);

            name = Validation.inputName("Name: ", "Eror: Invalid name! Can't contain special characters");
            quantity = Validation.getInt("Quantity: ", "Input a positive integer!", 0);
            sold = Validation.getInt("Sold: ", "Input a non-negative integer!", 0, quantity);
            price = Validation.getDouble("Price: ");
                
            if (K == -1) prodList.addLast(new Product(pcode, name, quantity, sold, price));
            else prodList.addPos(K, new Product(pcode, name, quantity, sold, price));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int searchProductByCode(String scode) throws Exception {
        for (int i = 0; i < prodList.size(); ++i) {
            if (prodList.get(i).getCode().equalsIgnoreCase(scode)) {
                return i;
            }
        }
        return -1;
    }
    
    public void searchProductByCodeFunc() throws Exception {
        if (prodList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________SEARCH A PRODUCT BY CODE___________");
        halt = 0;
        do {
            searchProductByCode();
            halt = (Validation.confirmContinue("Continue searching? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_________________________________");
        } while (halt == 0);
    }
    
    public void searchProductByCode() throws Exception {
        String findCode = Validation.inputCode("Product code: ", "Please input valid code! (e.g: P01)");

        int found = 0;
        for (int i = 0; i < prodList.size(); ++i) {
            String curCode = prodList.get(i).getCode();
            if (curCode.equalsIgnoreCase(findCode) == true) {
                System.out.println("The product you search: ");
                prodList.get(i).showInfo();
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("NOT FOUND! The product " + findCode + " does not exist.");
        }
    }
    
    public void removeProducByCode() throws Exception {
        if (prodList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________REMOVE A PRODUCT BY ID___________");
        String pcode;
        int pos;

        pcode = Validation.inputCode("Product code: ", "Please input valid code! (e.g: P01)");
        pos = searchProductByCode(pcode);

        if (pos == -1) {
            System.out.println("NOT FOUND! Product with code " + pcode + " does not exist.");
        } else {
            System.out.print("Product to be removed: ");
            prodList.get(pos).showInfo();

            if (Validation.confirmContinue("\nAre you sure? (y/n)... ", "Invalid input") == 1) {
                prodList.removePos(pos);
                System.out.println("REMOVE SUCCESSFUL!");
            } else {
                System.out.println("REMOVE FAILED!");
                System.out.flush();
            }
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void sortAscendingByCode() throws Exception {
        if (prodList.isEmpty()) {
            emptyNotify();
            return;
        }
        int sz = prodList.size();
        for (int i = 0; i < sz; ++i) 
            for (int j = i + 1; j < sz; ++j) {
                int cmp = prodList.get(i).getCode().compareTo(prodList.get(j).getCode());
                if (cmp > 0) {
                    prodList.swapNodes(prodList.get(i), prodList.get(j));
                    //prodList.swap(i, j);
                }
            }
        System.out.println("The product list after sorting:");
        printProductList();
    }
    
    public void printProductList() throws Exception {
        if (prodList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("_____________________PRINT PRODUCT LIST_____________________");
        System.out.print(header);
        for (int i = 0; i < prodList.size(); ++i) {
            System.out.printf("%02d | ", i + 1);
            prodList.get(i).showInfo();
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void addAAfterKPosition() {
        System.out.println("________ADD A PRODUCT AFTER A POSITION________");
       
        int maxK = Math.max(0, prodList.size() - 1);
        int K = 0;
        if (prodList.isEmpty()) {
            System.out.println("The product list is empty!\nYou only can add at position 0!\n");
            K = -1;
        } else {
            K = Validation.getOpt("Enter position in [0..." + maxK + "]: ", "Enter a position in 0..." + maxK + "!\n", 0, maxK);
            ++K;
         }
        
        if (addNewProduct(K) == true) {
            System.out.println("ADD SUCCESSFUL!");
        } else {
            System.out.println("ADD FAILED!");
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void saveToFile() throws Exception {
        System.out.println("________SAVE PRODUCT LIST TO FILE________");
        String filename = Validation.inputString("File name: ", "Can't contain special chars");
        filename = filename.trim();
        filename += ".txt";
        try {
            if (prodList.isEmpty()) {
                System.out.println("The product is empty. The program will save an empty list!");
                FileIO.saveProduct(filename, "", prodList);
            } else {
                FileIO.saveProduct(filename, header, prodList);
                System.out.println("SAVE SUCCESSFUL!");
            }
        } catch (IOException e) {
            System.out.println("SAVE FAILED!");
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void removeProductAfterAProductCode() throws Exception {
        if (prodList.isEmpty()) {
            emptyNotify();
            return;
        }
        String xCode = Validation.inputCode("Enter product code: ", "Please input valid code! (e.g: P01)");
        int pos = searchProductByCode(xCode);
        if (pos == -1) {
            System.out.println("NOT FOUND! The product with ID " + xCode + " does not exist!");
        } else if (pos == prodList.size() - 1) {            
            System.out.println("This product is that last item in the list!\nNo product will be removed!");
        } else {
            System.out.print("Product to be removed: ");
            prodList.get(pos + 1).showInfo();

            if (Validation.confirmContinue("\nAre you sure? (y/n)... ", "Invalid input") == 1) {
                prodList.removePos(pos + 1);
                System.out.println("REMOVE SUCCESSFUL!");
            } else {
                System.out.println("REMOVE FAILED!");
            }
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
}
