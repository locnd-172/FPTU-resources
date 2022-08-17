package util;

import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);
     
    public static int getAnInteger(String inpMsg, String errMsg) {
        int n;
        do {
            try {
                System.out.print(inpMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
            
        } while (true);
    }
    
    public static double getADouble(String inpMsg, String errMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inpMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }


}
