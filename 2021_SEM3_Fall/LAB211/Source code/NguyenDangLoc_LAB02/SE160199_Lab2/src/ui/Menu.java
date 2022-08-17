package ui;

import java.util.ArrayList;
import util.Validation;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Menu {
    private String title;
    private ArrayList<String> menuList = new ArrayList<>();
    
    public Menu(String title) {
        this.title = title;
    }

    public void addOpt(String newOpt) {
        menuList.add(newOpt);
    }

    public void printMenu() {
        if (menuList.isEmpty()) {
            System.out.println("There is no option added yet!");
            return;
        }
        System.out.println(title);
        System.out.println("Select the options below:");
        for (String opt : menuList) 
            System.out.println(opt);
    }

    public int chooseOpt() {
        int maxOpt = menuList.size();

        String inpMsg = "Select [1..." + maxOpt + "]: ";
        String errMsg = "Input an integer in [1..." + maxOpt + "]!";

        return Validation.getInt(inpMsg, errMsg, 1, maxOpt);
    }
    
    public int size() {
        return menuList.size();
    }
}
