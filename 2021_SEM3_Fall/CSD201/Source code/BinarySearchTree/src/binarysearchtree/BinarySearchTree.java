package binarysearchtree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BSTExecution();
        
        AVLTreeExecution();
        
        SimpleBalanceAlgorithm();
        
    }

    public static void BSTExecution() {
        //int[] a = {5, 4, 3, 7, 2, 9, 6, 8, 15, 12};
        System.out.println("BINARY SEARCH TREE");
        int[] a = {6, 3, 8, 1, 2, 4, 7, 9};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < a.length; i++) {
            bst.add(a[i]);
        }
        System.out.print("NLR: ");
        bst.NLR(bst.root);
        System.out.println("\nNLR without recursion: ");
        bst.NLRwithoutRecursion();

        System.out.print("\nLNR: ");
        bst.LNR(bst.root);

        System.out.print("\nLRN: ");
        bst.LRN(bst.root);
        System.out.println("");
        
        System.out.print("\nLeaf of tree: ");
        int x = bst.countLeaf();
        System.out.println("(" + x + (x > 1 ? " leaves)" : " leaf)"));
        System.out.print("Leaf of tree (recursion): ");
        x = bst.countLeafRec(bst.root);
        System.out.println("(" + x + (x > 1 ? " leaves)" : " leaf)"));

        System.out.print("\nNode has 1 child: ");
        x = bst.nodeHasOneChildRec(bst.root);
        System.out.print("(" + x + ")");

        System.out.print("\nNode has 2 children: ");
        x = bst.nodeHasTwoChildrenRec(bst.root);
        System.out.print("(" + x + ")");
        System.out.println("");
        
        System.out.println("\nHeight: " + bst.getHeight());
        
        System.out.print("\nBFS: ");
        bst.printBFS(bst.root);

        System.out.print("\nBFS recursion: ");
        LinkedList<Node<Integer>> Q = new LinkedList<>();
        Q.add(bst.root);
        bst.BFSRec(Q);

        System.out.println("");
        System.out.println("\nNode at each level");
        bst.printEachLevel(bst.root);
        System.out.println("");
        
        // Delete node
        int delNode = 6;
        boolean p = bst.deleteNodeCopy(delNode);
        System.out.print("\nDelete node " + delNode + " of BST");
        System.out.print("\nLNR: ");
        bst.LNR(bst.root);

        System.out.print("\nBFS: ");
        bst.printBFS(bst.root);

        System.out.println("\n_______________________________________");
    }

    public static void AVLTreeExecution() {
        System.out.println("AVL TREE");
        int[] a = {5, 4, 3, 7, 2, 9, 6, 8, 15, 12};
        AVL<Integer> avl = new AVL<>();
        for (int i = 0; i < a.length; i++) {
            avl.addAVL(a[i]);
        }

        System.out.print("NLR: ");
        avl.NLR(avl.root);
        System.out.println("\nNode at each level:");
        avl.printEachLevel(avl.root);
        
        // delete node
        int delNode = 3;
        avl.removeNode(delNode);
        System.out.print("\nAVL after remove " + delNode);
        System.out.print("\nNLR: ");
        avl.NLR(avl.root);
        
        System.out.println("\n_______________________________________");
    }

    public static void SimpleBalanceAlgorithm() {
        System.out.println("SIMPLE BALANCE ALGORITHM");
        Integer[] a = {5, 1, 9, 8, 7, 10, 2, 3, 4, 6};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < a.length; i++) {
            bst.add(a[i]);
        }

        System.out.println("BFS of original BST: ");
        bst.printBFS(bst.root);

        System.out.println("");

        Arrays.sort(a);

        System.out.println("");
        BST<Integer> balanceTree = new BST<>();
        balanceTree.balance(a);

        System.out.println("BFS of BST after balance: ");
        balanceTree.printBFS(balanceTree.root);
        
        System.out.println("\n_______________________________________");
    }
}
