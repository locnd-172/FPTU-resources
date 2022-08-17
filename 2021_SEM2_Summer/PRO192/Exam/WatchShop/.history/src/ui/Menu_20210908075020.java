package ui;

import java.util.ArrayList;
import util.Input;

public class Menu {
    private String title;
    private ArrayList<String> menuList = new ArrayList();

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
        for (String opt : menuList) {
            System.out.println(opt);
        }
    }

    public int chooseOpt() {
        int maxOpt = menuList.size();

        String inpMsg = "Enter [1..." + maxOpt + "]: ";
        String errMsg = "Please input a valid integer in [1..." + maxOpt + "]!";

        return Input.getAnInteger(inpMsg, errMsg, 1, maxOpt);
    }
}
