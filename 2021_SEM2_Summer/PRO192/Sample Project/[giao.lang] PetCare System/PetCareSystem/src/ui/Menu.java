package ui;

import java.util.ArrayList;
import util.MyToys;

public class Menu {
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();
 
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
        
    public void addNewOption(String newOption) {
        //TODO: cần kiểm tra coi option đưa vào có trùng với option
        //sẵn có hay ko?
        //nếu ko trùng, add vào danh sách lựa chọn
        optionList.add(newOption);        
    }
    
 
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("\n------------------------------------");
        System.out.println("Welcome to " + menuTitle);
        for (String x : optionList)
            System.out.println(x);  
    }
    
   
    public int getChoice() {
        int maxOption = optionList.size();
        
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption; 
        return MyToys.getAnInteger(inputMsg, errorMsg, 1, maxOption);
        
    }
}
