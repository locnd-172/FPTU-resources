package doublylinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node {
    int data;
    Node next;
    Node prev;

    Node() {
    }

    Node(int data) {
        this.data = data;
    }

    public Node(int data, Node p) {
        this.data = data;
        this.next = p;
        this.prev = p;
    }
    
}
