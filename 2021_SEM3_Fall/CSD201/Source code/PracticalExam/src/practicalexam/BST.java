package practicalexam;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

class Node<T> {

    T infor;
    Node<T> left;
    Node<T> right;

    public Node() {
    }

    public Node(T infor) {
        this.infor = infor;
    }

    public Node(T infor, Node<T> left, Node<T> right) {
        this.infor = infor;
        this.left = left;
        this.right = right;
    }

}

class BST<T extends Comparable<T>> {

    Node<T> root = null;

    public BST() {
    }

    boolean isEmpty() {
        return root == null;
    }

    void add(T el) {
        Node<T> tmp = root;
        Node<T> pre = null;
        Node<T> p = new Node<T>(el);
        if (isEmpty()) {
            root = p;
        } else {
            while (tmp != null) {
                pre = tmp;
                if (el.compareTo(tmp.infor) == 1) {
                    tmp = tmp.right;
                } else {
                    tmp = tmp.left;
                }
            }
            if (el.compareTo(pre.infor) == 1) {
                pre.right = p;
            } else {
                pre.left = p;
            }
        }
    }

    boolean isPerfectSquare(int x) {
        int s = (int) Math.sqrt(x);
        return (s * s == x);
    }

    boolean isFibonacci(T x) {
        int n = (Integer) x;
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    TreeSet<Integer> fibo = new TreeSet<>();
    
    void findFibo(Node<T> tmp) {
        NLR(tmp);
        
        for (Integer x : fibo) {
            System.out.print(x + ",");
        }
    }
    
    void NLR(Node<T> tmp) {
        if (tmp != null) {
            //System.out.print(tmp.infor + " ");
            if (isFibonacci(tmp.infor)) fibo.add((Integer) tmp.infor);
            NLR(tmp.left);
            NLR(tmp.right);
        }
    }

    void LNR(Node<T> tmp) {
        if (tmp != null) {
            LNR(tmp.left);
            System.out.print(tmp.infor + " ");
            LNR(tmp.right);
        }
    }

    void LRN(Node<T> tmp) {
        if (tmp != null) {
            LRN(tmp.left);
            LRN(tmp.right);
            System.out.print(tmp.infor + " ");
        }
    }

    int getLeafCount(Node<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            System.out.print(node.infor + "  ");
            return 1;
        } else {
            return getLeafCount(node.left) + getLeafCount(node.right);
        }
    }

    int countNodeHasTwoChildren(Node<T> p) {
        if (p == null) {
            return 0;
        }
        if (p.left != null && p.right != null) {
            System.out.print(p.infor + "   ");
            return 1 + countNodeHasTwoChildren(p.left) + countNodeHasTwoChildren(p.right);
        }
        return countNodeHasTwoChildren(p.left) + countNodeHasTwoChildren(p.right);
    }

    int get1Child(Node<T> p) {
        if (p == null) {
            return 0;
        }
        if (p.left == null ^ p.right == null) {
            System.out.print(p.infor + " ");
            return 1 + get1Child(p.left) + get1Child(p.right);
        }
        return get1Child(p.left) + get1Child(p.right);
    }

    int height(Node<T> p) {
        if (p == null) {
            return 0;
        } else {
            int a = height(p.left);
            int b = height(p.right);
            if (a > b) {
                return a + 1;
            }
            return b + 1;
        }
    }

    void NLRLoop(Node<T> p) {
        if (p == null) {
            return;
        }
        Stack<Node<T>> st = new Stack<>();
        st.push(p);
        while (!st.isEmpty()) {
            p = st.pop();
            System.out.print(p.infor + " ");
            if (p.right != null) {
                st.push(p.right);
            }
            if (p.left != null) {
                st.push(p.left);
            }
        }
    }

}
