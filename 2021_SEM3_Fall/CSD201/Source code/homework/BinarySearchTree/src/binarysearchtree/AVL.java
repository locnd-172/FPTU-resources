package binarysearchtree;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class AVL<T extends Comparable<T>> extends BST {

    Node<T> root = null;

    public Node<T> rightRotate(Node<T> cur) {
        Node<T> leftChild = cur.left;
        Node<T> rightOfLeft = leftChild.right;
        cur.left = rightOfLeft;
        leftChild.right = cur;
        return leftChild;
    }

    public Node<T> leftRotate(Node<T> cur) {
        Node<T> rightChild = cur.right;
        Node<T> leftOfRight = rightChild.left;
        cur.right = leftOfRight;
        rightChild.left = cur;
        return rightChild;
    }

    public Node<T> leftRightRotate(Node<T> cur) {
        cur.left = leftRotate(cur.left);
        return rightRotate(cur);
    }

    public Node<T> rightLeftRotate(Node<T> cur) {
        cur.right = rightRotate(cur.right);
        return leftRotate(cur);
    }

    public int getBalance(Node<T> cur) {
        if (cur == null) {
            return 0;
        }
        return height(cur.right) - height(cur.left);
    }

    public void addAVL(T x) {
        root = insertNode(root, x);
    }

    public Node<T> insertNode(Node<T> cur, T x) {
        if (cur == null) {
            cur = new Node(x);
        }

        int cmp = x.compareTo(cur.data);
        if (cmp < 0) {
            cur.left = insertNode(cur.left, x);
        } else if (cmp > 0) {
            cur.right = insertNode(cur.right, x);
        } else {
            return cur;
        }

        int balance = getBalance(cur);
        if (balance < -1) {
            Node<T> toLeft = cur.left;
            if (x.compareTo(toLeft.data) < 0) {
                return rightRotate(cur);
            } else if (x.compareTo(toLeft.data) > 0) {
                cur.left = leftRotate(cur.left);
                return rightRotate(cur);
            }
        }
        if (balance > 1) {
            Node<T> toRight = cur.right;
            if (x.compareTo(toRight.data) > 0) {
                return leftRotate(cur);
            } else if (x.compareTo(toRight.data) < 0) {
                cur.right = rightRotate(cur.right);
                return leftRotate(cur);
            }
        }
        return cur;
    }

    public void removeNode(T x) {
        root = deleteNode(root, x);
    }

    public Node<T> deleteNode(Node<T> cur, T x) {
        if (cur == null) return cur;

        int cmp = x.compareTo(cur.data);
        if (cmp < 0) {
            cur.left = deleteNode(cur.left, x);
        } else if (cmp > 0) {
            cur.right = deleteNode(cur.right, x);
        } else {
            deleteNodeCopy(x);
        }

        if (cur == null) return cur;

        int balance = getBalance(cur);
        if (balance < -1) {
            Node<T> toLeft = cur.left;
            int cmp2 = x.compareTo(toLeft.data);
            if (cmp2 < 0) return rightRotate(cur);
            else if (cmp2 > 0) return leftRightRotate(cur);
        }
        if (balance > 1) {
            Node<T> toRight = cur.right;
            int cmp2 = x.compareTo(toRight.data);
            if (cmp2 > 0) return leftRotate(cur);
            else if (cmp2 < 0) return rightLeftRotate(cur);
        }
        return cur;
    }
}
