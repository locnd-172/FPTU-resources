package VaccineInjectionManagement;

import container.VaccineInjectionList;
import ui.Menu;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class VaccineInjectionManagement {

    public static void main(String[] args) {
        System.out.println("Welcome to Covid-19 Vaccine Injection Management - @2021 by <SE160199 - Nguyen Dang Loc>");

        Menu mainMenu = new Menu("VACCINE INJECTION MANAGEMENT");

        mainMenu.addOpt("1. Display student list");
        mainMenu.addOpt("2. Add injection information");
        mainMenu.addOpt("3. Update injection information");
        mainMenu.addOpt("4. Delete an injection");
        mainMenu.addOpt("5. Search an injection by student ID");
        mainMenu.addOpt("6. Quit");

        VaccineInjectionList injectionList = new VaccineInjectionList();
        int opt;

        do {
            System.out.println("_________________________________________");
            mainMenu.printMenu();
            opt = mainMenu.chooseOpt();

            switch (opt) {
                case 1 -> injectionList.printStudentList();
                case 2 -> injectionList.addInjection();
                case 3 -> injectionList.updateInjection();
                case 4 -> injectionList.removeInjectionById();
                case 5 -> injectionList.searchInjection();
                case 6 -> System.out.println("\nGOODBYE!\n");
                default -> System.out.println("Enter a number in [1..." + mainMenu.size() + "]!");
            }
        } while (opt != 6);
    }
    
}
