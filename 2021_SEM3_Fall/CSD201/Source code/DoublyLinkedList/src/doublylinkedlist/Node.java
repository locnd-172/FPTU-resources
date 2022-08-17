package doublylinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node<T> {
    T data;
    Node<T> next, prev;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node nextNode, Node prevNode) {
        this.data = data;
        this.next = nextNode;
        this.prev = prevNode;
    }
    
}
