package FoodManagement;

import ui.Menu;
import data.FoodList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class FoodManagement {

    public static void main(String[] args) {
        System.out.println("Welcome to Food Management - @2021 by <SE160199 - Nguyen Dang Loc>");

        Menu mainMenu = new Menu("FOOD MANAGEMENT");

        mainMenu.addOpt("1. Add a new food");
        mainMenu.addOpt("2. Search a food by name");
        mainMenu.addOpt("3. Remove the food by ID");
        mainMenu.addOpt("4. Print the food list descending by expired date");
        mainMenu.addOpt("5. Save to file");
        mainMenu.addOpt("6. Quit");

        FoodList myFoodList = new FoodList();
        int opt;

        do {
            mainMenu.printMenu();
            opt = mainMenu.chooseOpt();

            switch (opt) {
                case 1 -> myFoodList.addNewFoodFunc();
                case 2 -> myFoodList.searchFoodByNameFunc();
                case 3 -> myFoodList.removeFoodById();
                case 4 -> myFoodList.printFoodListDescendingByExpDate();
                case 5 -> myFoodList.saveToFile();
                case 6 -> System.out.println("\nGOODBYE!\n");
                default -> System.out.println("Enter a number in [1..." + mainMenu.size() + "]!");
            }
        } while (opt != 6);
    }

}
