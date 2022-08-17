package binarysearchtree;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node<T> {
    T data;
    Node left, right;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean hasOneChild() {
        return (left != null && right == null) || (left == null && right != null);
    }
    
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
    
}
