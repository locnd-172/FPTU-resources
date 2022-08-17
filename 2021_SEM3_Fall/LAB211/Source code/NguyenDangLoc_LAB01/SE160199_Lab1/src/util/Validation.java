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

    public static double getDouble(String inpMsg) {
        Double num;
        String errMsg = "Error: Can't contain letters!\nMust be a positive double!";
        while (true) {
            try {
                System.out.print(inpMsg);
                String tmp = sc.nextLine().trim();
                if (tmp.isEmpty()) {
                    System.err.println("Can't be left blank.");
                    continue;
                }
                
                num = Double.parseDouble(tmp);
                if (num <= 0) {
                    System.err.println("Error: Negative/zero value!\nMust be a positive double!");
                    continue;
                }
                return num;
            } catch (NumberFormatException e) {
                System.err.println(errMsg);
            }
        }
    }

    public static String inputId(String inpMsg, String errMsg) {
        String id;
        String pattern = "([A-Z]{1})(\\d{3})";
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
    
    public static String inputPlace(String inpMsg) {
        String errMsg = "Input a valid integer in [1...3]";
        int type;
        while (true) {
            try {
                System.out.print(inpMsg);
                String tmp = sc.nextLine().trim();
                if (tmp.isEmpty()) {
                    System.err.println("Can't be left blank.");
                    continue;
                }
                if (tmp.matches("[a-zA-Z]+")) {
                    System.err.println("Error: Can't contain alphabet letter!");
                    System.err.println(errMsg);
                    continue;
                }
                type = Integer.parseInt(tmp);
                if (type == 1) return "cool";
                else if (type == 2) return "freeze";
                else if (type == 3) return "ice";
                else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.err.println("Error: Invalid input!");
                System.err.println(errMsg);
            }
        }
    }
    
    public static String inputType(String inpMsg) {
        String type;
        String pat = "[a-z]*";      
        while (true) {
            try {
                System.out.print(inpMsg);
                type = sc.nextLine().trim().toLowerCase();
                if (type.isEmpty()) {
                    System.err.println("Can't be left blank.");
                    continue;
                }
                if (type.indexOf(" ") != -1 && type.indexOf(" ") < type.length()) {
                    throw new Exception("One word only!");
                }
                if (!type.matches(pat)) {
                    throw new Exception("Error: Can't contain numbers or special characters!");
                }
                return type;
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\nSelect one of a type in the list.");
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
                    throw new Exception("Eror: Invalid name! Can't contain special characters!");
               
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
 
    public static String inputDate(String inpMsg) {
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
              
                int cmp = f.parse(inpDate).compareTo(f.parse(today));
                if (cmp < 0) 
                    throw new Exception("Error: Input date before today! Today is " + today);
                
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