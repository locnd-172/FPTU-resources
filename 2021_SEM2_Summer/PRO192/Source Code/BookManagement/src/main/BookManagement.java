package main;

import data.Shelf;
import ui.Menu;

public class BookManagement {
    
    public static void main(String[] args) {
        Menu mainMenu = new Menu("BOOK MANAGEMENT PROGRAM");
        mainMenu.addOpt("1. Add a new book");
        mainMenu.addOpt("2. Print the book list");
        mainMenu.addOpt("3. Sort the book list");
        mainMenu.addOpt("4. Exit");
        
        Shelf myShelf = new Shelf();
        
        int opt;
        do {
            mainMenu.printMenu();
            opt = mainMenu.chooseOpt();
            
            switch (opt) {
                case 1:
                    myShelf.inputABook();
                    break;
                case 2:
                    myShelf.printBookList();
                    break;
                case 3:
                    myShelf.sortBookList();
                    break;
                case 4:
                    System.out.println("Good bye!");
                    break;
            }
            System.out.println("______________________");
            
        } while (opt != 4);
    }
    
}
