package binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class BST<T extends Comparable<T>> {
    Node<T> root = null;
    
    public BST() {
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    // O(height)
    // Allow duplicate
    public void add(T x) { 
        Node<T> newNode = new Node(x);
        if (isEmpty()) {
            root = newNode;
        } else {
            Node<T> curNode = root;
            Node<T> preNode = null;
            while (curNode != null) {
                preNode = curNode;
                if (x.compareTo(curNode.data) > 0) curNode = curNode.right;
                else curNode = curNode.left;
            }
            if (x.compareTo(preNode.data) > 0) preNode.right = newNode;
            else preNode.left = newNode;
        }
    }
    
    public void addMany(T... arr) {
        for (T x : arr) {
            add(x);
        }
    }

    public void NLR(Node<T> cur) {
        if (cur == null) return;
        System.out.print(cur.data + " ");
        NLR(cur.left);
        NLR(cur.right);
    }
    
    public void LNR(Node<T> cur) {
        if (cur == null) return;
        LNR(cur.left);
        System.out.print(cur.data + " ");
        LNR(cur.right);
    }
    
    public void LRN(Node<T> cur) {
        if (cur == null) return;
        LRN(cur.left);
        LRN(cur.right);
        System.out.print(cur.data + " ");
    }
    
    public void NLRwithoutRecursion() {
        if (isEmpty()) return;
        Stack<Node<T>> St = new Stack<>();
        St.add(root);
        while (!St.isEmpty()) {
            Node<T> curNode = St.peek(); St.pop();
            System.out.print(curNode.data + " ");
            if (curNode.right != null) St.add(curNode.right);
            if (curNode.left != null) St.add(curNode.left);
        }
    }
    
    public void printBFS(Node<T> cur) {
        if (cur == null) {
            System.out.println("EMPTY TREE!");
            return;
        }
        Queue<Node<T>> Q = new LinkedList<>();
        Q.add(cur);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.poll(); 
            System.out.print(curNode.data + " ");
            if (curNode.left != null) Q.add(curNode.left);
            if (curNode.right != null) Q.add(curNode.right);
        }
    }
    
    public void BFSRec(LinkedList<Node<T>> Q) {
        if (Q.isEmpty()) return;
        Node<T> cur = Q.removeFirst();
        System.out.print(cur.data + " ");
        if (cur.left != null) Q.add(cur.left);
        if (cur.right != null) Q.add(cur.right);
        BFSRec(Q);
    }

    public void printEachLevel(Node<T> cur) {
        class NodeLevel<T> {
            Node<T> node;
            int level;
            NodeLevel(Node<T> p, int L) {
                node = p;
                level = L;
            }
        }
        
        if (cur == null) return;
        
        LinkedList<NodeLevel> Q = new LinkedList<>();
        NodeLevel curNode;
        int height = 0, lastLv = 1;
        
        Q.add(new NodeLevel(cur, 1));
        System.out.print(lastLv + ": ");
        while (!Q.isEmpty()) {
            curNode = Q.removeFirst();
            int curLv = curNode.level;
            
            if (lastLv != curLv) System.out.printf("\n" + curLv + ": ");
            System.out.print(curNode.node.data + " ");
            
            lastLv = curLv;
            Node<T> leftNode = curNode.node.left;
            Node<T> rightNode = curNode.node.right;
            
            if (leftNode != null)
                Q.add(new NodeLevel(leftNode, curLv + 1));
            if (rightNode != null)
                Q.add(new NodeLevel(rightNode, curLv + 1));
        }
    }
    
    public int getHeight() {
        return height(root);
    }
    
    public int height(Node<T> root) {
	if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    
    public int countLeaf() {
        if (isEmpty()) return 0;
        int cnt = 0;
        LinkedList<Node<T>> Q = new LinkedList<>();
        Q.addLast(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.removeFirst();
            if (curNode.isLeaf() == true) {
                ++cnt;
                System.out.print(curNode.data + " ");
            }
            if (curNode.left != null) Q.addLast(curNode.left);
            if (curNode.right != null) Q.addLast(curNode.right);
        }
        return cnt;
    }
    
    public int countLeafRec(Node<T> cur) {
        if (cur == null) return 0;
        if (cur.isLeaf()) {
            System.out.print(cur.data + " ");
            return 1;
        }
        return countLeafRec(cur.left) + countLeafRec(cur.right);
    }
     
    public int nodeHasOneChild() {
        if (isEmpty()) return 0;
        int cnt = 0;
        Queue<Node<T>> Q = new LinkedList<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.poll();
            if (curNode.hasOneChild() == true) {
                ++cnt;
                System.out.print(curNode.data + " ");
            }
            if (curNode.left != null) Q.add(curNode.left);
            if (curNode.right != null) Q.add(curNode.right);
        }
        return cnt;
    }
    
    public int nodeHasOneChildRec(Node<T> cur) {
        if (cur == null) return 0;
        if (cur.hasOneChild()) {
            System.out.print(cur.data + " ");
            return 1 + nodeHasOneChildRec(cur.left) + nodeHasOneChildRec(cur.right);
        } else 
            return nodeHasOneChildRec(cur.left) + nodeHasOneChildRec(cur.right);
    }
    
    public int nodeHasTwoChildren() {
        if (isEmpty()) return 0;
        int cnt = 0;
        Queue<Node<T>> Q = new LinkedList<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.poll(); 
            if (curNode.hasTwoChildren() == true) {
                ++cnt;
                System.out.print(curNode.data + " ");
            }
            if (curNode.left != null) Q.add(curNode.left);
            if (curNode.right != null) Q.add(curNode.right);
        }
        return cnt;
    }
    
    public int nodeHasTwoChildrenRec(Node<T> cur) {
         if (cur == null) return 0;
        if (cur.left != null && cur.right != null) {
            System.out.print(cur.data + " ");
            return 1 + nodeHasTwoChildrenRec(cur.left) + nodeHasTwoChildrenRec(cur.right);
        } else 
            return nodeHasTwoChildrenRec(cur.left) + nodeHasTwoChildrenRec(cur.right);
    }
   
    // O(height)
    public Node<T> searchNode(T x) {
        Node<T> curNode = root;
        while (curNode != null) {
            if (x.compareTo(curNode.data) == 0) return curNode;
            if (x.compareTo(curNode.data) < 0) 
                curNode = curNode.left;
            else
                curNode = curNode.right;
        }
        return curNode;
    }
    
    public Node<T> getFather(T x) {
        Node<T> curNode = root, preNode = null;
        while (curNode != null) {
            preNode = curNode;
            if (x.compareTo(curNode.data) > 0) 
                curNode = curNode.right;
            else 
                curNode = curNode.left;
            if (x.compareTo(curNode.data) == 0)  break;
        }
        return preNode;
    }
    
    public boolean removeLeaf(T x) {
        Node<T> delNode = searchNode(x);
        if (delNode.isLeaf() == false) return false;
        if (delNode == root && root.isLeaf())
            root = null;
        else {
            Node<T> parNode = getFather(x);
            if (parNode.left == delNode)
                parNode.left = null;
            else
                parNode.right = null;
        }
        return true;
    }
    
    public boolean removeOneChildNode(T x) {
        Node<T> delNode = searchNode(x);
        if (delNode == null || delNode.hasOneChild() == false)
            return false;
        if (delNode == root) {
            if (root.left == null)
                root = root.right;
            else
                root = root.left;
        }
        else {
            Node<T> grandFather = getFather(x);
            Node<T> grandChild;
            if (delNode.left == null)
                grandChild = delNode.right;
            else
                grandChild = delNode.left;
            
            if (delNode == grandFather.left)
                grandFather.left = grandChild;
            else
                grandFather.right = grandChild;
        }
        return true;
    }
    
    public Node<T> deleteRec(Node<T> delNode, T data) {
        if (delNode == null) return delNode;
 
        if (data.compareTo(delNode.data) < 0)
            delNode.left = deleteRec(delNode.left, data);
        else if (data.compareTo(delNode.data) > 0)
            delNode.right = deleteRec(delNode.right, data);
        else {
            if (delNode.left == null) return delNode.right;
            else if (delNode.right == null) return delNode.left;
 
            delNode.right = deleteRec(delNode.right, delNode.data);
        }
        return delNode;
    }
    
    public Node<T> leftMostRoot(Node<T> curNode) {
        return curNode.right;
    }
    
    public Node<T> leftMost(T x) {
        Node<T> curNode = searchNode(x);
        Node<T> leftMost = leftMostRoot(curNode);
        while (leftMost.left != null)
            leftMost = leftMost.left;
        return leftMost;
    }
    
    public boolean deleteByMerging(T x) {
        Node<T> delNode = searchNode(x);
        if (!delNode.hasTwoChildren()) return false;
        
        Node<T> grandFather = getFather(x);
        Node<T> leftGrandChild = delNode.left;
        
        Node<T> leftMostNode = leftMost(x);
        leftMostNode.left = leftGrandChild;
        
        if (delNode == root) {
            root = root.left;
        }
        else {
            Node<T> toRight = grandFather.right;
            T rightData = toRight.data;
            if (x.compareTo(rightData) == 0) 
                grandFather.right = leftMostNode;
            else
                grandFather.left = leftMostNode;
        }
        return true;
    }
    
    public boolean deleteNodeMerge(T x) {
        Node<T> delNode = searchNode(x);
        if (delNode == null) return false;
        
        if (delNode.isLeaf())
            return removeLeaf(x);
        
        if (delNode.hasOneChild())
            return removeOneChildNode(x);
        
        return deleteByMerging(x);
    }
    
    public boolean deleteByCopying(T x) {
        Node<T> delNode = searchNode(x);
        if (!delNode.hasTwoChildren()) return false;
        
        Node<T> leftMostNode = leftMost(x);
        T rm = leftMostNode.data;
        
        if (leftMostNode.isLeaf()) removeLeaf(rm);
        if (leftMostNode.hasOneChild()) removeOneChildNode(rm);
        
        delNode.data = leftMostNode.data;
        return true;
    }
    
    public boolean deleteNodeCopy(T x) {
        Node delNode = searchNode(x);
        if (delNode == null) 
            return false;
        
        if (delNode.isLeaf()) 
            return removeLeaf(x);
        
        if (delNode.hasOneChild()) 
            return removeOneChildNode(x);
        
        return deleteByCopying(x);
    }
    
    public void simpleBalanceAlgo(T[] data, int L, int R) {
        if (L > R) return;
 
        int mid = (L + R) / 2;
        add(data[mid]);
        
        simpleBalanceAlgo(data, L, mid - 1);
        simpleBalanceAlgo(data, mid + 1, R);
 
    }

    public void balance(T[] data) {
        simpleBalanceAlgo(data, 0, data.length - 1);
    }
}
