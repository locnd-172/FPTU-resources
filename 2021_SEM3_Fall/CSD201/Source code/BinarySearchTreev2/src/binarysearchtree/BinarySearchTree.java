package binarysearchtree;

import java.util.LinkedList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        int []a = {5, 4, 3, 7, 2, 9, 6, 5, 15, 12};
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
        
        System.out.println("\nHeight: " + bst.getHeight());
        
        System.out.print("BFS: ");
        bst.printBFS();
        
        System.out.print("\nLeaf of tree: ");
        bst.printLeaves();
        
        System.out.print("\nNode has 1 child: ");
        bst.printNodeHasOneChild();
        
        System.out.print("\nNode has 2 children: ");
        bst.printNodeHasTwoChildren();
        
        System.out.println("\nNode at each level");
        bst.printEachLevel();
        System.out.println("");
            
        LinkedList<Node<Integer>> Q = new LinkedList<>();
        Q.add(bst.root);
        bst.BFS(Q);

        System.out.println("");
        System.out.print("BFS: ");
        bst.printBFS();

        System.out.println("");
        bst.nodeHasOneChild(bst.root);

    }
    
}
