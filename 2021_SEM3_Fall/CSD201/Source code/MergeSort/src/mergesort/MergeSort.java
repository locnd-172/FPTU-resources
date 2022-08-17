package mergesort;

import java.util.ArrayList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class MergeSort {
    static void merge(ArrayList<Integer> a, int L, int mid, int R) {
        if (mid < L || R < mid) return;
        
        int n = R - L + 1;
        int[] b = new int[n];
        
        int i = L, j = mid + 1, k = 0;
        
        while (i <= mid && j <= R) {
            if (a.get(i) < a.get(j)) b[k++] = a.get(i++);
            else b[k++] = a.get(j++);
        }
        while (i <= mid) b[k++] = a.get(i++);
        while (j <= R) b[k++] = a.get(j++);
        
        k = 0;
        for (i = L; i <= R; i++) 
            a.set(i, b[k++]);
    }

    static void mergeSort(ArrayList<Integer> a, int L, int R) {
        if (L >= R) return;
        int mid = (L + R) / 2;
        mergeSort(a, L, mid);
        mergeSort(a, mid + 1, R);
        merge(a, L, mid, R);
    }
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(5);
        a.add(6);
        a.add(1);
        a.add(10);
        a.add(15);
        a.add(3);
        a.add(12);
        a.add(11);
        a.add(7);
        
        mergeSort(a, 0, a.size() - 1);
        System.out.println(a);
    }
    
   
    
    
    static void merge2(ArrayList<Integer> a, int L, int mid, int R) {
        int n1 = mid - L + 1;
        int n2 = R - mid;
  
        int low[] = new int[n1];
        int high[] = new int[n2];
  
        for (int i = 0; i < n1; ++i) low[i] = a.get(L + i);
        for (int j = 0; j < n2; ++j) high[j] = a.get(mid + 1 + j);
  
        int i = 0, j = 0;
        int k = L;
        while (i < n1 && j < n2) {
            if (low[i] <= high[j]) a.set(k, low[i++]);
            else a.set(k, high[j++]);
            k++;
        }
  
        while (i < n1) a.set(k++, low[i++]);
        while (j < n2) a.set(k++, high[j++]);
    }
    
    static void merge3(ArrayList<Integer> a, int L, int mid, int R) {
        if (mid < L || R < mid) return;
        
        int n = R - L + 1;
        ArrayList<Integer> b = new ArrayList<>();
        for (int i = 0; i < n; ++i) b.add(0);
        
        int i = L, j = mid + 1, k = 0;
        
        while (i <= mid && j <= R) {
            if (a.get(i) < a.get(j)) b.set(k++, a.get(i++));
            else b.set(k++, a.get(j++));
        }
        while (i <= mid) b.set(k++, a.get(i++));
        while (j <= R) b.set(k++, a.get(j++));
        
        k = 0;
        for (i = L; i <= R; i++) 
            a.set(i, b.get(k++));
    }
}
