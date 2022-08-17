package singlelinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node {
    int info;
    Node next;

    Node() {
    }

    Node(int info) {
        this.info = info;
    }

    public Node(int info, Node p) {
        this.info = info;
        this.next = p;
    }
}
