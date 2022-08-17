package practicalexam;

import practicalexam.BST.*;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Prob3 {

    public Prob3() {
    }

    void solve() {
        BST<Integer> bst = new BST<>();
        int[] a = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int i = 0; i < a.length; i++) {
            bst.add(a[i]);
        }
        bst.findFibo(bst.root);
        System.out.println("");
        
        
    }
    
    
}
