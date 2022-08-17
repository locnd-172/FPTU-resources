package salemanagementsystem;

import list.CustomerList;
import list.OrderList;
import list.ProductList;
import ui.Menu;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class SaleManagementSystem {

    public static Menu mainMenu = new Menu("SALE MANAGEMENT SYSTEM");

    public static Menu prodMenu = new Menu("PRODUCTS MENU");
    public static Menu cusMenu = new Menu("CUSTOMERS MENU");
    public static Menu ordMenu = new Menu("ORDERS MENU");
    
    public static ProductList prodList = new ProductList();
    public static CustomerList cusList = new CustomerList();
    public static OrderList ordList = new OrderList();
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Welcome to Sale Management System - @2021 by <SE160199 - Nguyen Dang Loc>");
        
        addMenuOption();
        
        int opt;
        do {
            mainMenu.printMenu();
            opt = mainMenu.chooseOpt();

            switch (opt) {
                case 1 -> productdMenu();
                case 2 -> customerMenu();
                case 3 -> orderMenu();
                case 4 -> System.out.println("\nGOODBYE!\n");
                default -> System.out.println("Enter a number in [1...4]!");
            }
        } while (opt != 4);
    }
    
    public static void addMenuOption() {
        // Main menu
        mainMenu.addOpt("1. Product menu");
        mainMenu.addOpt("2. Customer menu");
        mainMenu.addOpt("3. Order menu");
        mainMenu.addOpt("4. Quit");
        
        // Product menu
        prodMenu.addOpt("1. Load data from file");
        prodMenu.addOpt("2. Add a product");
        prodMenu.addOpt("3. Show product list");
        prodMenu.addOpt("4. Save to file ");
        prodMenu.addOpt("5. Search by product code");
        prodMenu.addOpt("6. Delete by product code");
        prodMenu.addOpt("7. Sort product list by product code");
        prodMenu.addOpt("8. Add product after a position in list");
        prodMenu.addOpt("9. Delete product after product having xCode");
        prodMenu.addOpt("10. Back");
        
        // Customer menu
        cusMenu.addOpt("1. Load data from file");
        cusMenu.addOpt("2. Add a customer");
        cusMenu.addOpt("3. Show customer list");
        cusMenu.addOpt("4. Save to file");
        cusMenu.addOpt("5. Search by customer code");
        cusMenu.addOpt("6. Delete by cusstomer code");
        cusMenu.addOpt("7. Back");
        
        // Order menu
        ordMenu.addOpt("1. Input data");
        ordMenu.addOpt("2. Display data with total value");
        ordMenu.addOpt("3. Sort by product code + customer code");
        ordMenu.addOpt("4. Back ");
        
    }
    
    public static void productdMenu() throws Exception {
        int opt;
        do {
            line();
            prodMenu.printMenu();
            opt = prodMenu.chooseOpt();

            switch (opt) {
                case 1 -> prodList.loadData();
                case 2 -> prodList.addNewProductFunc();
                case 3 -> prodList.printProductList();
                case 4 -> prodList.saveToFile();
                case 5 -> prodList.searchProductByCodeFunc();
                case 6 -> prodList.removeProducByCode();
                case 7 -> prodList.sortAscendingByCode();
                case 8 -> prodList.addAAfterKPosition();
                case 9 -> prodList.removeProductAfterAProductCode();
                case 10 -> line();
                default -> System.out.println("Enter a number in [1...10]!");
            }
        } while (opt != 10);
    }
    
    public static void customerMenu() throws Exception {
        int opt;
        do {
            line();
            cusMenu.printMenu();
            opt = cusMenu.chooseOpt();

            switch (opt) {
                case 1 -> cusList.loadData();
                case 2 -> cusList.addNewCustomerFunc();
                case 3 -> cusList.printCustomerList();
                case 4 -> cusList.saveToFile();
                case 5 -> cusList.searchCustomerByCodeFunc();   
                case 6 -> cusList.removeProducByCode();
                case 7 -> line();
                default -> System.out.println("Enter a number in [1...7]!");
            }
        } while (opt != 7);
    }
    
    public static void orderMenu() throws Exception {
        int opt;
        do {
            line();
            ordMenu.printMenu();
            opt = ordMenu.chooseOpt();

            switch (opt) {
                case 1 -> ordList.inputOrderingDataFunc(prodList.prodList, cusList.cusList);
                case 2 -> ordList.printOrderingList();
                case 3 -> ordList.sortAscendingByCode();
                case 4 -> line();
                default -> System.out.println("Enter a number in [1...4]!");
            }
        } while (opt != 4);
    }
    
    public static void line() {
        System.out.println("_________________________________");
    }
}
