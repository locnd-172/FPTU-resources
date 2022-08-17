package ui;

import java.util.Scanner;

public class Menu {
    private String title;
    private String option[] = new String[4];
    private static Scanner sc = new Scanner(System.in);
    int count = 0;
    
    public Menu(String title) {
        this.title = title;
    }
    
    public void addOpt(String newOpt) {
        option[count] = newOpt;
        ++count;
    }
    
    public void printMenu() {
        System.out.println(title);
        for (String opt : option) {
            System.out.println(opt);
        }
    }
    
    public int chooseOpt() {
        int opt;
        do {
            try {
                System.out.print("Enter [1..." + count + "]: ");
                opt = Integer.parseInt(sc.nextLine());
                return opt;
            } catch (Exception e) {
                System.out.println("Input an integer [1..." + count + "]!");
            }
            
        } while (true);
    }
}
