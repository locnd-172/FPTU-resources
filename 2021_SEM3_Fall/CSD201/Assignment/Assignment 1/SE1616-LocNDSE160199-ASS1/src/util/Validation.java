package util;

import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Validation {
    private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String inpMsg, String errMsg, int min) {
        int num;
        while (true) {
            try {
                System.out.print(inpMsg);
                String tmp = sc.nextLine().trim();
                if (tmp.isEmpty()) {
                    System.err.println("Can't be left blank.");
                    continue;
                }
                
                num = Integer.parseInt(tmp);
                if (num < min) {
                    System.err.println("Can't be lower than " + min + "!");
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
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
                if (num < min) {
                    System.err.println("Can't be lower than " + min + "!");
                    throw new Exception();
                }
                if (max < num) {
                    System.out.println("Can't be greater than current quantity! (" + max + ")");
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
    public static int getOpt(String inpMsg, String errMsg, int min, int max) {
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

    public static String inputCode(String inpMsg, String errMsg) {
        String id;
        String pattern = "([A-Z]{1})(\\d{2})";
        while (true) {
            try {
                System.out.print(inpMsg);
                id = sc.nextLine().trim().toUpperCase();
                if (id.isEmpty() || id.isBlank()) {
                    System.err.println("Can't be left blank!");
                    continue;
                }
                if (!id.matches(pattern)) {
                    throw new Exception();
                } else {
                    return id;
                }
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
    
    public static String capitalizeName(String name) {
        String words[] = name.split("\\s");
        String finalName = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String rest = w.substring(1);
            finalName += first.toUpperCase() + rest + " ";
        }
        return finalName.trim();
    }
    
    public static String inputName(String inpMsg, String errMsg) {
        String text;
        while (true) {
            try {
                System.out.print(inpMsg);
                text = sc.nextLine().trim();
                text = text.replaceAll("\\s{2,}", " ");
                text = capitalizeName(text);
                if (text.isEmpty()) {
                    System.err.println("Can't be left blank");
                    continue;
                }
                if (containSpecialChar(text)) {
                    throw new Exception();
                } else 
                    return text;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
    public static String inputString(String inpMsg, String errMsg) {
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
                if (containSpecialChar(text)) {
                    throw new Exception();
                } else 
                    return text;
            } catch (Exception e) {
                System.err.println(errMsg);
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

    public static String inputPhone(String inpMsg) {
        String pat = "[0-9]{4}";
        String text;
        while (true) {
            try {
                System.out.print(inpMsg);
                text = sc.nextLine().trim().toLowerCase();
                if (text.isEmpty()) {
                    System.err.println("Can't be left blank");
                    continue;
                }
                if (text.matches(pat) == false) 
                    throw new Exception();
                return text;
            } catch (Exception e) {
                System.err.println("Error: Wrong phone format!\nPhone includes 4 digits!");
            }
        }
    }
    
}
