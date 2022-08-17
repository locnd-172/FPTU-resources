/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;

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
    public void add(T el) { 
       Node<T> newNode = new Node(el);
        if (isEmpty()) {
            root = newNode;
        } else {
            Node<T> curNode = root;
            Node<T> preNode = null;
            while (curNode != null) {
                preNode = curNode;
                if (el.compareTo(curNode.data) > 0) curNode = curNode.right;
                else curNode = curNode.left;
            }
            if (el.compareTo(preNode.data) > 0) preNode.right = newNode;
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
        LinkedList<Node<T>> St = new LinkedList<>();
        St.addLast(root);
        while (!St.isEmpty()) {
            Node<T> curNode = St.removeLast();
            System.out.print(curNode.data + " ");
            if (curNode.right != null) St.addLast(curNode.right);
            if (curNode.left != null) St.addLast(curNode.left);
        }
        
    }
    
    public void BFS(LinkedList<Node<T>> Q) {
        if (Q.isEmpty()) return;
 
        Node<T> cur = Q.removeFirst();
        System.out.print(cur.data + " ");
 
        if (cur.left != null) Q.add(cur.left);
        if (cur.right != null) Q.add(cur.right);
 
        BFS(Q);
    }

    public int printLeaves() {
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
    
    public int countLeaf(Node<T> cur, int cnt) {
        if (cur == null) return cnt;
        if (cur.isLeaf()) {
            System.out.print(cur.data + " ");
            ++cnt;
        }
        if (cur.left != null) countLeaf(cur.left, cnt);
        if (cur.right != null) countLeaf(cur.right, cnt);
        return cnt;
    }
    
    // lecturer recorrect
    public int nodeLeaf(Node<T> p) {
        if (p != null) {
            if (p.left == null && p.right == null) {
                System.out.print(p.data + " ");
                return 1;
            } else return nodeLeaf(p.left) + nodeLeaf(p.right);
                
        } else return 0;
    }
    
    // lecturer recorrect
    public int nodeHasOneChild(Node<T> p) {
        if (p != null) {
            if (p.left == null ^ p.right == null) {
                System.out.print(p.data + " ");
                return 1 + nodeHasOneChild(p.left) + nodeHasOneChild(p.right);
            } else return nodeHasOneChild(p.left) + nodeHasOneChild(p.right);
                    
        } else return 0;
    }
    
    public int nodeHasTwoChildren(Node<T> p) {
         if (p != null) {
            if (p.left != null && p.right != null) {
                System.out.print(p.data + " ");
                return 1 + nodeHasTwoChildren(p.left) + nodeHasTwoChildren(p.right);
            } else return nodeHasTwoChildren(p.left) + nodeHasTwoChildren(p.right);
                    
        } else return 0;
    }
    
    public int getHeight(Node cur, int depth) {
        if (cur == null) {
            return depth;
        }
        return Math.max(getHeight(cur.left, depth + 1), 
                        getHeight(cur.right, depth + 1));
    }

    public int getHeight() {
        return getHeight(root, 0);
    }
    
    public int height(Node<T> root) {
	if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    
//    int countLeafTwoChildren(Node<T> p) {
//        if (p != null) {
//            if ((p.left != null && p.right != null)) {
//                System.out.print(p.info + " ");
//                return 1 + countLeafTwoChildren(p.left) + countLeafTwoChildren(p.right);
//            } else {
//                return 0;
//            }
//        } else {
//            return 0;
//        }
//    }
    
    public int printNodeHasOneChild() {
        if (isEmpty()) return 0;
        int cnt = 0;
        LinkedList<Node<T>> Q = new LinkedList<>();
        Q.addLast(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.peekFirst(); Q.removeFirst();
            if (curNode.hasOneChild()== true) {
                ++cnt;
                System.out.print(curNode.data + " ");
            }
            if (curNode.left != null) Q.addLast(curNode.left);
            if (curNode.right != null) Q.addLast(curNode.right);
        }
        return cnt;
    }
    
    public int printNodeHasTwoChildren() {
        if (isEmpty()) return 0;
        int cnt = 0;
        LinkedList<Node<T>> Q = new LinkedList<>();
        Q.addLast(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.peekFirst(); Q.removeFirst();
            if (curNode.hasTwoChildren()== true) {
                ++cnt;
                System.out.print(curNode.data + " ");
            }
            if (curNode.left != null) Q.addLast(curNode.left);
            if (curNode.right != null) Q.addLast(curNode.right);
        }
        return cnt;
    }
    
    public void printBFS() {
        if (isEmpty()) {
            System.out.println("EMPTY TREE!");
            return;
        }
        LinkedList<Node<T>> Q = new LinkedList<>();
        Q.addLast(root);
        while (!Q.isEmpty()) {
            Node<T> curNode = Q.peekFirst(); Q.removeFirst();
            System.out.print(curNode.data + " ");
            if (curNode.left != null) Q.addLast(curNode.left);
            if (curNode.right != null) Q.addLast(curNode.right);
        }
    }
    // O(height)
    public Node<T> search(T x) {
        Node<T> res = root;
        while (res != null) {
            if (x.compareTo(res.data) == 0) return res;
            else if (x.compareTo(res.data) < 0) res = res.left;
            else res = res.right;
        }
        return null;
    }
    
    public int getHeightBFS() {
        // inner class
        class NodeLevel<T> {
            Node<T> node;
            int level;
            NodeLevel(Node<T> p, int L) {
                node = p;
                level = L;
            }
        }
        if (root == null) return 0;
        
        LinkedList<NodeLevel> Q = new LinkedList<>();
        NodeLevel cur;
        int height = 0;
        
        // root -> hei = 1
        Q.add(new NodeLevel(root, 1));
        
        while (!Q.isEmpty()) {
            cur = Q.removeFirst();
            int curLv = cur.level;
            if (height < curLv) height = curLv;
            
            Node<T> leftNode = cur.node.left;
            Node<T> rightNode = cur.node.right;
            
            if (leftNode != null)
                Q.add(new NodeLevel(leftNode, curLv + 1));
            if (rightNode != null)
                Q.add(new NodeLevel(rightNode, curLv + 1));
        }
        return height;
    }
    
    public void printEachLevel() {
        // inner class
        class NodeLevel<T> {
            Node<T> node;
            int level;
            NodeLevel(Node<T> p, int L) {
                node = p;
                level = L;
            }
        }
        
        if (root == null) return;
        
        LinkedList<NodeLevel> Q = new LinkedList<>();
        NodeLevel curNode;
        int height = 0, lastLv = 1;
        
        // root -> hei = 1
        Q.add(new NodeLevel(root, 1));
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
    
    public Node<T> searchNode(T x) {
        Node<T> curNode = root;
        while (curNode != null) {
            if (x.compareTo(curNode.data) == 0)
                return curNode;
            if (x.compareTo(curNode.data) < 0)
                curNode = curNode.left;
            else
                curNode = curNode.right;
        }
        return null;
    }
    
    private boolean removeOneChildNode(T el) {
        Node<T> delNode = new Node(el);
        if (delNode == null || !delNode.hasOneChild())
            return false;
        if (delNode == root) {
            if (root.left == null)
                root = root.right;
            else
                root = root.left;
        }
        else {
            Node<T> travNode = root;
            Node<T> preNode = null;
            while (travNode != null) {
                preNode = travNode;
                if (el.compareTo(travNode.data) > 0) travNode = travNode.right;
                else travNode = travNode.left;
                if (el.compareTo(travNode.data) == 0)  break;
            }
            Node grandFather = preNode;
            Node grandChild;
            if (delNode.left == null)
                grandChild = delNode.right;
            else
                grandChild = delNode.left;
            
            if (delNode == grandFather.left)
                grandFather.left = grandChild;
            else
                grandFather.right = grandChild;
            //grandChild.father = grandFather;
        }
        return true;
    }
    
    private boolean removeLeaf(T el) {
        Node<T> delNode = new Node(el);
        if (!delNode.isLeaf()) return false;
        if (delNode == root && root.isLeaf())
            root = null;
        else {
            Node<T> travNode = root;
            Node<T> preNode = null;
            while (travNode != null) {
                preNode = travNode;
                if (el.compareTo(travNode.data) > 0) travNode = travNode.right;
                else travNode = travNode.left;
                if (el.compareTo(travNode.data) == 0)  break;
            }
            Node parNode = preNode;
            if (parNode.left == delNode)
                parNode.left = null;
            else
                parNode.right = null;
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
 
            // successor (smallest in the right subtree)
            //delNode.data = minValue(delNode.right);
 
            delNode.right = deleteRec(delNode.right, delNode.data);
        }
        return delNode;
    }
    
    public Node<T> getFather(Node<T> curNode) {
        Node<T> iNode = root, father = null;
        T data = curNode.data;
        while (iNode != null) {
            father = iNode;
 
            int cmp = data.compareTo(iNode.data);
            if (cmp < 0) iNode = iNode.left;
            else iNode = iNode.right;
            if (cmp == 0) break;
        }
        return father;
    }
    
    public Node<T> leftMostRoot(Node<T> curNode) {
        return curNode.right;
    }
    
    public Node<T> leftMost(T el) {
        Node<T> curNode = new Node(el);
        Node<T> leftMost = leftMostRoot(curNode);
        while (leftMost.left != null)
            leftMost = leftMost.left;
        return leftMost;
    }
    
    public boolean deleteByMerging(T x) {
        Node<T> delNode = new Node(x);
        if (delNode.isLeaf() || delNode.hasOneChild())
            return false;
        
        Node grandFather = getFather(delNode);
        Node leftGrandChild = delNode.left;
        
        Node<T> leftMostNode = leftMost(x);
        leftMostNode.left = leftGrandChild;
        
        if (delNode == root) {
            root = root.left;
        }
        else {
            if (grandFather.left == delNode)
                grandFather.left = leftGrandChild;
            else
                grandFather.right = leftGrandChild;
        }
        return true;
    }
    
    public boolean deleteNodeMerge(T x) {
        Node<T> delNode = searchNode(x);
        if (delNode == null)
            return false;
        
        if (delNode.isLeaf())
            return removeLeaf(x);
        
        if (delNode.hasOneChild())
            return removeOneChildNode(x);
        
        return deleteByMerging(x);
    }
    
    private boolean deleteByCopying(T x) {
        Node<T> delNode = new Node(x);
        if (!delNode.hasTwoChildren()) return false;
        
        Node<T> leftMostNode = leftMost(x);
        delNode.data = leftMostNode.data;
        
        if (leftMostNode.isLeaf()) removeLeaf(x);
        if (leftMostNode.hasOneChild()) removeOneChildNode(x);
        
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
    
    
    public Node<T> simpleBalanceAlgo(ArrayList<Node<T>> nodes, int L, int R) {
        if (L > R) return null;
 
        int mid = (L + R) / 2;
        Node<T> curNode = nodes.get(mid);
 
        curNode.left = simpleBalanceAlgo(nodes, L, mid - 1);
        curNode.right = simpleBalanceAlgo(nodes, mid + 1, R);
 
        return curNode;
    }
}
