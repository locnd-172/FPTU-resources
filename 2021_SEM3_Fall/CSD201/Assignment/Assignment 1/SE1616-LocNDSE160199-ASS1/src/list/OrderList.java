package list;

import data.Customer;
import data.Order;
import data.Product;
import java.util.Scanner;
import singlelinkedlist.SLL;
import util.Validation;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class OrderList extends SLL<Order> {
    private SLL<Order> ordList = new SLL();
    private Scanner sc = new Scanner(System.in);
    private int halt = 0;
    private String header = String.format("%-3s| %-6s| %-6s| %3s\n",
                                            "No", "PCODE", "CCODE", "QUANTITY");
    
     public void emptyNotify() {
        System.out.println("The ordering list is empty.");
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void printOrderingList() throws Exception {
        if (ordList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________ORDERING LIST___________");
        System.out.print(header);
        for (int i = 0; i < ordList.size(); ++i) {
            System.out.printf("%02d | ", i + 1);
            ordList.get(i).showInfo();
        }
        Validation.getEnter("ENTER to back to previous...");
    }
    
    public void inputOrderingDataFunc(SLL<Product> prodList, SLL<Customer> cusList) throws Exception {
        System.out.println("_________ORDER A PRODUCT_________");
        halt = 0;
        if (prodList.isEmpty()) {
            System.out.println("The product list is empty!");
            halt = 1;
        }
        if (cusList.isEmpty()) {
            System.out.println("The customer list is empty!");
            halt = 1;
        }
        while (halt == 0) {
            printProductList(prodList);
            if (inputOrderingData(prodList, cusList) == true) {
                System.out.println("ORDER SUCCESSFUL!");
            } else {
                System.out.println("ORDER FAILED!");
            }
            halt = (Validation.confirmContinue("\nContinue ordering? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            if (halt == 0) System.out.println("_________________________________");
        } 
    }
    
    public boolean inputOrderingData(SLL<Product> prodList, SLL<Customer> cusList) throws Exception {
        String pcode, ccode;
        int ordQuantity;
        String errMsg = "";
        try {
            pcode = Validation.inputCode("Product code: ", "Error: Wrong code format!\nCode includes 1 uppercase letters + 3 digits (e.g: P001)!");
            ccode = Validation.inputCode("Customer code: ", "Error: Wrong code format!\nCode includes 1 uppercase letters + 3 digits (e.g: C001)!");
            ordQuantity = Validation.getInt("Quantity: ", "Input a non-negative value!", 0);

            int findPCode = searchPCode(prodList, pcode);
            int findCCode = searchCCode(cusList, ccode);

            int cont = 1;
            if (findPCode == -1) {
                errMsg = "Product with code " + pcode + " odoes not exist!";
                cont = 0;
            }
            if (findCCode == -1) {
                errMsg += (cont == 0 ? "\n" : "") + "Customer with code " + ccode + " does not exist!";
                cont = 0;
            }
            if (cont == 0) throw new Exception(errMsg);
            

            int pos = searchOrderByCode(pcode, ccode);
            if (pos != -1) {
                throw new Exception("This product code and customer code already exist!");
            }
               
            int curQuantity = prodList.get(findPCode).getQuantity();
            int saled = prodList.get(findPCode).getSaled();
            
            if (curQuantity == saled) {
                throw new Exception("Product " + pcode + " is exhausted!");
            }

            int remQuantity = curQuantity - saled;

            if (ordQuantity <= remQuantity) {
                ordList.addLast(new Order(pcode, ccode, ordQuantity));
                
                // update sold
                int newSaled = ordQuantity + saled;
                prodList.get(findPCode).setSaled(newSaled); 
            } else {
                String prodName = prodList.get(findPCode).getName();
                throw new Exception("Remain quantity of " + prodName + " is lower than " + ordQuantity);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void printProductList(SLL<Product> prodList) throws Exception {
        String pheader = String.format("%-3s| %-5s| %-15s| %-9s| %-6s| %-6s| %-10s\n",
            "No", "CODE", "NAME", "QUANTITY", "SOLD", "PRICE", "TOT. VALUE");
        System.out.print(pheader);
        for (int i = 0; i < prodList.size(); ++i) {
            System.out.printf("%02d | ", i + 1);
            prodList.get(i).showInfo();
        }
    }
     
    public int searchOrderByCode(String pcode, String ccode) throws Exception {
        for (int i = 0; i < ordList.size(); ++i) {
            if (ordList.get(i).getCcode().equalsIgnoreCase(ccode) && ordList.get(i).getPcode().equalsIgnoreCase(pcode)) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchPCode(SLL<Product> prodList, String pcode) throws Exception {
        for (int i = 0; i < prodList.size(); ++i) {
            if (prodList.get(i).getCode().equalsIgnoreCase(pcode))
                return i;
        }
        return -1;
    }
    
    public int searchCCode(SLL<Customer> cusList, String ccode) throws Exception {
         for (int i = 0; i < cusList.size(); ++i) {
            if (cusList.get(i).getCode().equalsIgnoreCase(ccode))
                return i;
        }
        return -1;
    }
    
    public void sortAscendingByCode() throws Exception {
        if (ordList.isEmpty()) {
            emptyNotify();
            return;
        }
        int sz = ordList.size();
        for (int i = 0; i < sz; ++i) 
            for (int j = i + 1; j < sz; ++j) {
                int cmpProCode = ordList.get(i).getPcode().compareTo(ordList.get(j).getPcode());
                int cmpCusCode = ordList.get(i).getCcode().compareTo(ordList.get(j).getCcode());
                if (cmpProCode > 0 || (cmpProCode == 0 && cmpCusCode > 0)) {
                    ordList.swapNodes(ordList.get(i), ordList.get(j));
                }
            }
        System.out.println("The orrdering list after sorting:");
        printOrderingList();
    }
}
