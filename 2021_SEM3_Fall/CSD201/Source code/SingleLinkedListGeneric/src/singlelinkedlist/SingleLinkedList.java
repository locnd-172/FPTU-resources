package singlelinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class SingleLinkedList {

    public static void main(String[] args) throws Exception {
        SLL<String> sll = new SLL();

        sll.addFirst("");
        sll.addFirst("world");

        sll.addLast("eat");
        sll.addLast("sleep");
        sll.addLast("code");

        String[] a = {"list", "array", "collection"};
        sll.addMany(a);

        System.out.println("Size: " + sll.size());
        sll.printAll();
        System.out.println("");

        sll.addPos(8, "WWW");

        System.out.println("Size: " + sll.size());
        sll.printAll();
        System.out.println("");

        sll.removeFirst();
        sll.removeFirst();

        sll.removeLast();
        sll.removeLast();
        sll.removeLast();

        System.out.println("Size: " + sll.size());
        sll.printAll();
        System.out.println("");

        sll.removePos(2);
        sll.removePos(2);

        System.out.println("Size: " + sll.size());
        sll.printAll();
        System.out.println("");

//        sll.removeAll();
//        System.out.println("Size: " + sll.size());
//        sll.printAll();
//        System.out.println("");

         System.out.println(sll.get(1));
         System.out.println(sll.indexOf("sleep"));

    }

}
