package arrlistvsvector;
// Java program to Convert ArrayList to Vector

import java.time.LocalDate;
import java.util.*;

public class ArrListVsVector {

    private static int $;

    public static void main(String[] args) {
        /*ArrayList<String> listStrings = new ArrayList<>();

        listStrings.add("Geeks");
        listStrings.add("for");
        listStrings.add("Geeks");

        //listStrings.addAll(listStrings);
        
        // create empty vector object
        Vector<String> vStrings = new Vector<>(listStrings);

        // use the addAll method
        vStrings.addAll(listStrings);

        System.out.println("Vector contains: " + vStrings);*/

 /*String Str = new String("Rs 1000");
 
        // Printing original string
        System.out.print("The original string  is : ");
        System.out.println(Str);
 
        // using substring() to extract substring
        // returns 1000
        System.out.print("The extracted substring  is : ");
        System.out.println(Str.substring(3));*/
 /*Integer i = new Integer("12");
        System.out.println(i + 12);
        //modify(i);
        //System.out.println(i);
        
        String x = null + "hihi";
     
        System.out.println(x);
        
        System.out.printf("\\n");*/
 /*boolean b =true;
        String S = "hello";
        int i = 5;     
        
        System.out.format("%3$3b, %1$3d, %2$12s\n", i, S, b);*/
 /*ArrayList list = new ArrayList();
        list.add(15);
        list.add("hello");
        System.out.println(list);*/
 /*String s1 = "test string";
        String s2 = "test string";
        if (s1 == s2) System.out.println("same");
        else System.out.println("diff");*/
 /*Holder h = new Holder();
        h.held = 100;
        h.bump(h);
        System.out.println(h.held);*/
 /*int x = 10;
        int b = 2;
        int b2 = 3;
        b = b * b2;
        if ((x > 10 ? x++ : --x) < 10) {
            System.out.print("Foo");
        }
        if (x < 10) {
            System.out.print("Bar");
        }
        int myValue = 0;
        int x = 3;
        while (myValue++ < 3) {
            int y = (1 + 2 * myValue) % 3;
            System.out.println("   myValue:" + myValue);
            System.out.println("   y:" + y);

            switch (y) {
                default:
                case 0:
                    x -= 1;
                    break;
                case 1:
                    x += 5;
            }
            System.out.println("   x:" + x);
        }
        System.out.println(x);*/
 /*String My_Value = null;
        System.out.println($);
        System.out.println(My_Value);
        
        System.out.println(LocalDate.of(2015, 4, 1));*/
        //System.out.println(-9 % 5);
        int a = 7;
        int b = -7;
        int c = 0;
        System.out.println("7  = " + Integer.toBinaryString(a));
        System.out.println("-7 = " + Integer.toBinaryString(b));

        //signed shift
        c = a >> 2;
        System.out.println("7 >> 2  = " + Integer.toBinaryString(c));

        //unsigned shift
        c = a >>> 2;
        System.out.println("7 >>> 2 = " + Integer.toBinaryString(c));

        c = b >> 2;
        System.out.println("-7 >> 2  = " + c); //Integer.toBinaryString(c));

        c = b >>> 2;
        System.out.println("-7 >>> 2 = " + c); //Integer.toBinaryString(c));

    }

    private static void modify(Integer i) {
        i = i + 1;
    }

}

class Holder {

    public int held;

    public void bump(Holder theHolder) {
        theHolder.held++;
    }
}
