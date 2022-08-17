package singlelinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node<E> {
    E data;
    Node<E> next;

    public Node() {
    }

    public Node(E data) {
        this.data = data;
    }

    public Node(E data, Node nextNode) {
        this.data = data;
        this.next = nextNode;
    }
    
    public E getData() {
        return data;
    }
   
}
