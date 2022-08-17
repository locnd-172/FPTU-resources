package data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.Validation;
import java.lang.*;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class FoodList {

    private ArrayList<Food> foodList = new ArrayList();
    private Scanner sc = new Scanner(System.in);
    private int halt = 0;
    private String header = String.format("%-4s| %-8s| %-15s| %-12s| %-12s| %-8s| %-10s\n",
            "No", "ID", "NAME", "WEIGHT (kg)", "TYPE", "PLACE", "EXP. DATE (dd-mm-yyyy)");

    public void emptyNotify() {
        System.out.println("The refrigerator is empty.");
        Validation.getEnter("ENTER to back to main menu...\n");
        System.out.println("_________________________________________");
    }

    public void addNewFoodFunc() {
        System.out.println("_____________________ADD NEW FOOOD_____________________");
        System.out.println("ID includes 1 uppercase letters + 3 digits (e.g: V001)");
        halt = 0;
        do {
            if (addNewFood() == true) {
                System.out.println("ADD SUCCESSFUL!");
            } else {
                System.err.println("ADD FAILED!");
            }
            halt = (Validation.confirmContinue("Continue adding? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_______________________");
        } while (halt == 0);
    }

    public boolean addNewFood() {
        try {
            String id, name, type, place, expiredDate;
            double weight;

            int pos = 0;

            do {
                id = Validation.inputId("ID: ", "Error: Wrong ID format!\nID includes 1 uppercase letters + 3 digits (e.g: V001)!");
                pos = searchFoodById(id);
                if (pos != -1) {
                    System.err.println("This ID already exist! Enter another one!");
                }
            } while (pos != -1);

            name = Validation.inputString("Name: ");
            weight = Validation.getDouble("Weight (kg): ");
            type = Validation.inputType("Type (Vegetable, Fruit, Grain, Meat, Fish, Dairy, Others): ");
            place = Validation.inputPlace("Place (1.cool, 2.freeze, 3.ice. Choose a number): ");
            expiredDate = Validation.inputDate("Date (dd-mm-yyyy): ");

            foodList.add(new Food(id, name, weight, type, place, expiredDate));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void searchFoodByNameFunc() {
        if (foodList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________SEARCH A FOOD BY NAME___________");
        halt = 0;
        do {
            searchFoodByName();
            halt = (Validation.confirmContinue("Continue searching? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("_______________________");
        } while (halt == 0);
    }

    public int searchFoodById(String cmpId) {
        for (int i = 0; i < foodList.size(); ++i) {
            if (foodList.get(i).getId().equalsIgnoreCase(cmpId)) {
                return i;
            }
        }
        return -1;
    }
    
    public void searchFoodByName() {
        String findName = Validation.inputString("Name: ");
        findName = findName.toLowerCase();

        int found = 0;
        for (int i = 0; i < foodList.size(); ++i) {
            String curName = foodList.get(i).getName().toLowerCase();
            if (curName.contains(findName) == true) {
                foodList.get(i).showInfo();
                found = 1;
            }
        }
        if (found == 0) {
            System.err.println("NOT FOUND! This food does not exist.");
        }
    }

    public void removeFoodById() {
        if (foodList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________REMOVE A FOOD BY ID___________");
        String id;
        int pos;

        id = Validation.inputId("Enter ID: ", "Please input valid ID (e.g: VEG001)");
        pos = searchFoodById(id);

        if (pos == -1) {
            System.err.println("NOT FOUND! Food with ID " + id + " does not exist.");
        } else {
            System.out.print("Food to be removed: ");
            foodList.get(pos).showInfo();

            if (Validation.confirmContinue("\nAre you sure? (y/n)... ", "Input 'Y' / 'N'!") == 1) {
                foodList.remove(pos);
                System.out.println("REMOVE SUCCESSFUL!");
            } else {
                System.out.println("REMOVE FAILED!");
            }
        }
        Validation.getEnter("ENTER to back to main menu...\n");
    }

    public void sortDescendingByExpDate() {
        Comparator cmp = new Comparator<Food>() {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            @Override
            public int compare(Food o1, Food o2) {
                String d1 = o1.getExpiredDate();
                String d2 = o2.getExpiredDate();
                try {
                    return df.parse(d2).compareTo(df.parse(d1));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        };  
        Collections.sort(foodList, cmp);
    }

    public void printFoodListDescendingByExpDate() {
        if (foodList.isEmpty()) {
            emptyNotify();
            return;
        }
        sortDescendingByExpDate();
        System.out.println("_______________________PRINT FOOD LIST DESCENDING BY EXPIRED DATE_______________________");
        System.out.printf(header);

        for (int i = 0; i < foodList.size(); ++i) {
            System.out.printf("%-4d", i + 1);
            foodList.get(i).showInfo();
        }
        System.out.println("");
        Validation.getEnter("ENTER to back to main menu...\n");
    }

    public void saveToFile() {
        String filename = Validation.inputString("File name: ");
        filename += ".txt";
        try {
            File f = new File(filename);
            PrintWriter pw = new PrintWriter(f);
            String data;
            
            if (foodList.isEmpty()) {
                System.out.println("The refrigerator is empty. The program will save an empty list!");
                data = "The refrigerator is empty!";
                pw.print(data);
            } else {
                sortDescendingByExpDate();

                data = header + "\n";
                pw.print(data);

                for (int i = 0; i < foodList.size(); ++i) {
                    data = String.format("%-4d", i + 1);
                    data += foodList.get(i).toString() + "\n";
                    pw.print(data);
                }
            }
            
            System.out.println("SAVE SUCCESSFUL!");
            pw.close();
        } catch (IOException e) {
            System.err.println("SAVE FAILED!");
        }

        Validation.getEnter("ENTER to back to main menu...\n");
        System.out.println("_________________________________________");
    }

}
