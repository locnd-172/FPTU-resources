package usingstack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node<T> {
    T data;
    Node<T> next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node nextNode) {
        this.data = data;
        this.next = nextNode;
    }
    
    public T getData() {
        return data;
    }
   
}
