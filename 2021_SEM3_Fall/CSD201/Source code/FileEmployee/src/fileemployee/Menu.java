package fileemployee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

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
        Scanner sc = new Scanner(System.in);
        int op = Integer.parseInt(sc.nextLine());
        return op;
    }
}
