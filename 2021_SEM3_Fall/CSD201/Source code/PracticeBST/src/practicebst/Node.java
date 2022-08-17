package practicebst;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Node {
    int key;
    Node left, right;
    Node father;

    public Node(int key) {
        this.key = key;
        left = right = father = null;
    }

    @Override
    public String toString() {
        return "" + this.key;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean havingOneChild() {
        return (left != null && right == null) || (left == null && right != null);
    }
    
    public boolean having2Children() {
        return left != null && right != null;
    }

}
