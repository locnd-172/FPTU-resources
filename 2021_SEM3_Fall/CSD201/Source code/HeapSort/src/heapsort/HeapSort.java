package heapsort;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class HeapSort {

    public static void main(String[] args) {
        
        ArrayList<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(9);
        a.add(7);
        a.add(2);
        a.add(11);
        a.add(16);
        a.add(4);
        a.add(13);
        a.add(12);

        buildHeap(a);
        for (Integer x : a) System.out.print(x + " ");
            System.out.println("");
            System.out.println("");
        sort(a);
        
    }
    
    static void btmUpHeapify(ArrayList<Integer> a, int n, int i) {
        int cur = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && a.get(leftChild) > a.get(cur)) 
            cur = leftChild;

        if (rightChild < n && a.get(rightChild) > a.get(cur)) 
            cur = rightChild;

        if (cur != i) {
            Collections.swap(a, i, cur);
            btmUpHeapify(a, n, cur);
        }
    }

    static void buildHeap(ArrayList<Integer> a) {
        int n = a.size();
        int startIdx = (int) Math.floor((n - 1) / 2);

        for (int i = startIdx; i >= 0; i--) {
            btmUpHeapify(a, n, i);
        }
    }
    
    static void sort(ArrayList<Integer> a) {
        int n = a.size();
        for (int i = n - 1; i > 0; i--) {
            Collections.swap(a, 0, i);
            btmUpHeapify(a, i, 0);
            for (Integer x : a) System.out.print(x + " ");
            System.out.println("");
        }
    }
    
}
