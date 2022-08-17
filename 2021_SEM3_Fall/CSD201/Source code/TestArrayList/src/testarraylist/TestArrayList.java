package testarraylist;

import java.util.*;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class TestArrayList {

    public static void main(String[] args) {
        ArrayList<String> AL = new ArrayList<>();
        
        AL.add("hello");
        AL.add("world");
        AL.add("code");
        
        AL.add(2, "coffee");
        AL.add(3, "sleep");
        AL.add(2, "eat");
        
        //AL.clear();
        
        System.out.println("Empty: " + AL.isEmpty());
        System.out.println(AL.indexOf("eat"));
        System.out.println(AL.get(0));
        System.out.println(AL);
        
        AL.forEach((str) -> System.out.println(str));
        
    }

}
