
package fileemployee;

import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class FileEmployee {
    public static void main(String[] args) {
        String filename = "employees.txt";
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu("EMPLOYEE LSIT");
        
        menu.addOpt("1. Add employee");
        menu.addOpt("2. Remove an employee");
        menu.addOpt("3. Promote employee's salary");
        menu.addOpt("4. Print list");
        menu.addOpt("5. Save to file");
        menu.addOpt("6. Quit");
        
        int uChoice;
        boolean changed = false;
        EmpList list = new EmpList();
        
        list.addFromFile(filename);
        
        do {
            menu.printMenu();
            uChoice = menu.chooseOpt();
            switch (uChoice) {
                case 1: list.addNewEmp(); changed = true; break;
                case 2: list.removeEmp(); changed = true; break;
                case 3: list.promote();changed = true; break;
                case 4: list.printList();changed = true; break;
                case 5: list.saveToFile(filename);changed = true; break;
                default: if (changed) {
                    System.out.println("Save changes (Y/N) ? ");
                    String response = sc.nextLine().toUpperCase();
                    if (response.startsWith("Y")) 
                        list.saveToFile(filename);
                }
            }
        } while (uChoice>0 && uChoice<6);
        
        
    }
    
}
