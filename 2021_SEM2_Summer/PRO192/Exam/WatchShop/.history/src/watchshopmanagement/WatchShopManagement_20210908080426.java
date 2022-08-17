package watchshopmanagement;

import data.Cage;
import java.util.Scanner;
import ui.Menu;

public class WatchShopManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to dShop - \u00a9 2021 Copyright by SE1601999 - Nguyen Dang Loc");
        System.out.print("Login name: ");
        String guest = sc.nextLine();
        System.out.println("Hi " + guest);
        
        Menu mainMenu = new Menu("WATCH SHOP MANAGEMENT");
        mainMenu.addOpt("1. Add a watch");
        mainMenu.addOpt("2. Search a watch by ID");
        mainMenu.addOpt("3. Update price of a watch");
        mainMenu.addOpt("4. Print the watch list descending by price");
        mainMenu.addOpt("5. Exit");
        
        Cage myCage = new Cage();
        
        int opt;
        do {
            mainMenu.printMenu();
            opt = mainMenu.chooseOpt();
            
            switch (opt) {
                case 1:
                    myCage.addNewWatch();
                    break;
                case 2:
                    myCage.searchByID();
                    break;
                case 3:
                    myCage.updatePrice();
                    break;
                case 4:
                    myCage.printWatchListDescendingByPrice();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Enter a choice in [1...5]!");
                    break;
            }
            System.out.println("______________________");
            
        } while (opt != 5);
        
    }
    
}
