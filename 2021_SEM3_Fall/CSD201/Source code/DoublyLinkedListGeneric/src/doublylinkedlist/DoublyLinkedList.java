package doublylinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class DoublyLinkedList {

    public static void main(String[] args) {
        DLL dll = new DLL();
        dll.addFirst(5);
        dll.addFirst(8);
        dll.addFirst(6);
        
        dll.addLast(7);
        dll.addLast(9);
        dll.addLast(10);
        
        int a[] = {1, 0, 5};
        dll.addMany(a);
        
        System.out.println("Size of DLL: " + dll.size());
        dll.printAll();
        System.out.println("");
        
        dll.addPos(10, 11);
        
        System.out.println("Size of DLL: " + dll.size());
        dll.printAll();
        
        System.out.println("");
        
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        dll.removeLast();
        
        System.out.println("Size of DLL: " + dll.size());
        dll.printAll();
        
        
//        dll.removeFirst();
//        dll.removeFirst();
//        
//        dll.removeLast();
//        dll.removeLast();
//        dll.removeLast();
//        
//        dll.removePos(2);
//       
//        System.out.println("Size of DLL: " + dll.size());
//        dll.printAll();
//        System.out.println("");
//        System.out.println(dll.get(3));
//        dll.indexOf(5);
    }

}
