package singlelinkedlist;

import java.util.LinkedList;
import java.util.ArrayList;
/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */

public class SingleLinkedList {

    public static void main(String[] args) {
        SLL sll = new SLL();        
        sll.addFirst(5);
        sll.addFirst(4);
        sll.addFirst(9);
        
        sll.addLast(11);
        sll.addLast(13);
        sll.addLast(10);
        
        int a[] = {22, 34, 44};
        sll.addMany(a);
        
        System.out.println("Size of SLL: " + sll.size());
        sll.printAll();
        System.out.println("");
        
        sll.addPos(10, 9999);
       
        System.out.println("Size of SLL: " + sll.size());
        sll.printAll();
        System.out.println("");
        
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        sll.removeLast();
        
        System.out.println("Size of SLL: " + sll.size());
        sll.printAll();
        System.out.println("");
//        sll.removeFirst();
//        sll.removeFirst();
//        sll.removeLast();
//        sll.removeLast();
//        
//        sll.removePos(7);
//        System.out.println("");
//        System.out.println("Size of SLL: " + sll.size());
//        sll.printAll();
//        
//        System.out.println("");
//        System.out.println(sll.get(3));
//        System.out.println(sll.indexOf(9999));
//        
//        System.out.println("");
//        
//        sll.removeFirst();
//        sll.removeFirst();
//        sll.removeFirst();
//        sll.removeFirst();
//        sll.removeFirst();
//        sll.removeFirst();
//        
//        System.out.println("Size of SLL: " + sll.size());
//        sll.printAll();
    }
    
}
