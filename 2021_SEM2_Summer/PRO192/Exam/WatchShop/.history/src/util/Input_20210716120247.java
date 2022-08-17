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
    
    public static int getAnInteger(String inpMsg, String errMsg, int min, int max) {
        int n, tmp;
        if (min > max) {
            tmp = min;
            min = max;
            max = tmp;
        }
        while (true) {
            try {
                System.out.print(inpMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
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

    public static double getADouble(String inpMsg, String errMsg, double min, double max) {
        double  n, tmp;
        if (min > max) {
            tmp = min;
            min = max;
            max = tmp;
        }
        while (true) {
            try {
                System.out.print(inpMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < min || n > max)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    
    public static String getID(String inpMsg, String errMsg) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inpMsg);
            id = sc.nextLine().trim().toUpperCase();
          
            if (id.length() != 8)
                System.out.println(errMsg);
            else
                return id;            
        }
    }
    
    public static String getString(String inpMsg, String errMsg) {
        String id;        
        while (true) {
            System.out.print(inpMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errMsg);
            else 
                return id;
        }
    }
}
