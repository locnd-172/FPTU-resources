package testlinkedlist;

import java.util.LinkedList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> LL = new LinkedList<>();
        
        LL.add(0);
        LL.add(3);
        LL.add(8);
        
        LL.add(2, 11);
        LL.add(4, 84);
        LL.add(1, 27);
        
        int a[] = {5, 37, 9};
        LL.addAll(LL);
        LL.addAll(2, LL);
        
        LL.addFirst(999);
        LL.addLast(111);
        
        //LL.clear();
        
        //LinkedList<Integer> LL2 = (LinkedList<Integer>) LL.clone();
        //System.out.println(LL2);
        
        System.out.println(LL.contains(999));
        
        System.out.println(LL.element());
        
        System.out.println(LL.get(10));
        System.out.println(LL.getFirst());
        System.out.println(LL.getLast());
        
        System.out.println(LL.indexOf(27));

        System.out.println(LL.lastIndexOf(84));
        
        LL.offer(-5);
        LL.offerFirst(-10);
        LL.offerLast(500);
        
        LL.pop();
        LL.push(8888); // stack
        
        LL.remove();
        LL.remove(0);
        LL.removeFirstOccurrence(27);
        
        LL.set(20, -11111);
        
        
        System.out.println("Size: " + LL.size());
        System.out.println(LL);
        
                
    }
    
}
