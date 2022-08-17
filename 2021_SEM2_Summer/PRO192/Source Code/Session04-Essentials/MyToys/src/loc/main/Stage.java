package loc.main;

import loc.util.MyToys;

public class Stage {

    public static void main(String[] args) {
        System.out.println("Pi: " + MyToys.PI);
        //MyToys.PI = 4.0;
        System.out.println("Pi: " + MyToys.PI);
        
        int n = MyToys.getAnInteger();
        System.out.println("n: " + n);
        
        n = MyToys.getAnInteger("Imput your choices [1...7]: ");
        System.out.println("Yout choices: " + n);
        
        
    }
}
