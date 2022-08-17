package circularlinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class CircularLinkedList {

    public static void main(String[] args) throws Exception {
        CLL cll = new CLL();

        cll.addFirst(2);
        cll.addFirst(9);
        cll.addFirst(11);
        cll.addFirst(64);

        cll.addLast(7);
        cll.addLast(-100);
        cll.addLast(16);

        int a[] = {-7, 39, 43};
        cll.addMany(a);
        
        System.out.println("Size: " + cll.size());
        cll.printAll();
        System.out.println("");
        
        cll.addPos(10, 1000);
        
        System.out.println("Size: " + cll.size());
        cll.printAll();
        System.out.println("");
        
        cll.indexOf(43);
        System.out.println("");
        
        cll.removeFirst();
        cll.removeFirst();
        
        cll.removeLast();
        
        cll.removePos(8);
        
        System.out.println("Size: " + cll.size());
        cll.printAll();
        
        System.out.println("");
        
        cll.rotate(7);
        
        System.out.println("Size: " + cll.size());
        cll.printAll();
        
    }

}
