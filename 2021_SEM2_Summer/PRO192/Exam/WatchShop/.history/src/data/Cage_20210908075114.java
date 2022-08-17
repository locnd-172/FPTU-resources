package data;

import java.util.ArrayList;
import java.util.Scanner;
import util.Input;

public class Cage {

    private ArrayList<Watch> watchList = new ArrayList();
    private Scanner sc = new Scanner(System.in);

    public void addNewWatch() {
        String id, name, brandName, gender;
        double size, price;
        int pos;
        
        System.out.println("ID is a upper string of length 8 included char & integer!");
        do {
            id = Input.getID("ID: ", "Invalid input!");
            pos = searchById(id);
            if (pos != -1) {
                System.out.println("The watch id already exist! Enter another one, please!");
            }
        } while (pos != -1);

        name = Input.getString("Name: ", "The name is required!");
        brandName = Input.getString("Brand: ", "The brand name is required!");
        gender = Input.getString("Gender: ", "The gender is required!");

        size = Input.getADouble("Size: ", "The size is in [38...46]!", 38.0, 46.0);
        price = Input.getADouble("Price: ", "Enter a price in valid number format, please!");

        watchList.add(new Watch(id, name, brandName, gender, size, price));
    }

    public int searchById(String id) {
        if (watchList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < watchList.size(); i++) {
            if (watchList.get(i).getId().equalsIgnoreCase(id)) {
                return 1;
            }
        }

        return -1;
    }

    public void searchByID() {
        if (watchList.isEmpty()) {
            System.out.println("There is no watch!");
            return;
        }
        String id;
        Watch x;
        
        id = Input.getID("ID: ", "The ID is required!");
        x = searchWatchById(id);
        
        if (x == null)
            System.out.println("Not found!");
        else {
            System.out.println("Here is the watch you want:");
            x.showInfo();
        }
        
    }
    public Watch searchWatchById(String id) {
        if (watchList.isEmpty()) {
            return null;
        }

        for (int i = 0; i < watchList.size(); i++) {
            if (watchList.get(i).getId().equalsIgnoreCase(id)) {
                return watchList.get(i);
            }
        }

        return null;
    }

    public void updatePrice() {
        if (watchList.isEmpty()) {
            System.out.println("There is no watch!");
            return;
        }
      
        String id;
        double tmpPrice;
        Watch xw;

        id = Input.getID("ID: ", "The ID is required!");
        xw = searchWatchById(id);

        if (xw == null) {
            System.out.println("Not found!");
        } else {
            System.out.printf("The price before updating: %.1f VND\n", xw.getPrice());

      
            tmpPrice = Input.getADouble("New price: ", "The price is required!");

            xw.setPrice(tmpPrice);

            System.out.println("The price is updated successfully!");
        }
    }

    public void printWatchListDescendingByPrice() {
        if (watchList.isEmpty()) {
            System.out.println("There is no watch!");
            return;
        }
        for (int i = 0; i < watchList.size() - 1; i++) {
            for (int j = i + 1; j < watchList.size(); j++) {
                if (watchList.get(i).getPrice() < watchList.get(j).getPrice()) {
                    Watch tmp = watchList.get(i);
                    watchList.set(i, watchList.get(j));
                    watchList.set(j, tmp);
                    
                }
            }
        }

        System.out.println("The watch list after sorting descend by price:");
        System.out.printf("|%-11s|%-15s|%-8s|%-6s|%-8s|%-12s|\n",
                "ID", "NAME", "BRAND", "GENDER", "SIZE", "PRICE");
        for (Watch x : watchList) {
            x.showInfo();
        }
    }
}
