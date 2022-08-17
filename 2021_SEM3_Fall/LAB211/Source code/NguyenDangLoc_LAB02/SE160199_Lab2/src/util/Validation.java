package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Validation {

    private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String inpMsg, String errMsg, int min, int max) {
        int num;
        if (min > max) {
            int tmp = min; min = max; max = tmp;
        }
        while (true) {
            try {
                System.out.print(inpMsg);
                String tmp = sc.nextLine().trim();
                if (tmp.isEmpty()) {
                    System.err.println("Can't be left blank.");
                    continue;
                }
                
                num = Integer.parseInt(tmp);
                if (num < min || max < num) {
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }

    public static String inputId(String inpMsg, String errMsg, String ch) {
        String id = "";
        String pattern = ch + "(\\d{3})";
        while (true) {
            try {
                System.out.print(inpMsg);
                id = sc.nextLine().trim().toUpperCase();
                if (id.isEmpty() || id.isBlank()) {
                    System.err.println("Can't be left blank!");
                    continue;
                }
                
                if (!id.matches(pattern)) 
                    throw new Exception();
         
                return id;
              
            } catch (Exception e) {
                System.err.println(errMsg);
            }
            
        }
    }
    
    public static String inputStudentId(String inpMsg, String errMsg) {
        String id;
        String pattern = "([A-Z]{2})(\\d{6})";
        while (true) {
            try {
                System.out.print(inpMsg);
                id = sc.nextLine().trim().toUpperCase();
                if (id.isEmpty() || id.isBlank()) {
                    System.err.println("Can't be left blank!");
                    continue;
                }
                if (!id.matches(pattern)) 
                    throw new Exception();
         
                return id;
              
            } catch (Exception e) {
                System.err.println(errMsg);
            }
            
        }
    }
    
    public static boolean containSpecialChar(String inp) {
        String specialCharacters = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i = 0; i < inp.length(); ++i) {
            char ch = inp.charAt(i);
            if (specialCharacters.contains(Character.toString(ch))) {
                return true;
            }
        }
        return false;
    }
    
    public static String inputString(String inpMsg) {
        String text;
        while (true) {
            try {
                System.out.print(inpMsg);
                text = sc.nextLine().trim().toLowerCase();
                text = text.replaceAll("\\s{2,}", " ");
                if (text.isEmpty()) {
                    System.err.println("Can't be left blank");
                    continue;
                }
                if (containSpecialChar(text)) 
                    throw new Exception("Eror: Invalid place! Can't contain special characters!");
               
                return text;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static int confirmContinue(String inpMsg, String errMsg) {
        String text;
        while (true) {
            try {
                System.out.print(inpMsg);
                text = sc.nextLine().trim();
                text = text.toUpperCase();
                if (text.isEmpty()) {
                    System.err.println("Can't be left blank");
                    continue;
                }
                if (text.startsWith("Y")) {
                    return 1;
                } else if (text.startsWith("N")) {
                    return 0;
                } else 
                    throw new Exception();
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }

    public static boolean validDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        try {
            df.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;        
    }
 
    public static String inputDate(String inpMsg, int ord) {
        String inpDate;
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");  
        String today = f.format(new Date());
        while (true) {
            try {
                System.out.print(inpMsg);
                inpDate = sc.nextLine().trim();
                if (inpDate.isEmpty()) {
                    System.err.println("Can't be left blank");
                    continue;
                }
                if (validDate(inpDate) == false) 
                    throw new Exception("Error: Wrong date format/Date does not exist!");
                if (ord == 1) {
                    int cmp = f.parse(today).compareTo(f.parse(inpDate));
                    if (cmp < 0) 
                        throw new Exception("Error: Input date after today! Today is " + today);
                }
                
                
                return inpDate;
               
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static boolean isEmptyString(String s) {
        return s.trim().isEmpty();
    }

    public static void getEnter(String msg) {
        Scanner sca = new Scanner(System.in);
        System.out.print(msg);
        while (true) {
            if (isEmptyString(sca.nextLine())) {
                return;
            }
        }
    }
}