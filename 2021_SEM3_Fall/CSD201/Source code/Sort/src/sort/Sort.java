package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Sort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(6);
        a.add(4);
        a.add(9);
        a.add(10);
        a.add(8);
        a.add(3);
        a.add(7);
        a.add(5);
        
        Sort.quickSort(a);
        //insertionSort(a);
//        bubbleSort(a);
//        selectionSort(a);
    }
    
    public static void insertionSort(ArrayList<Integer> a) {
        
        int n = a.size();
        
        for (int i = 1; i < n; ++i) {
            int cur = a.get(i);
            int j = i - 1;
            while (j >= 0 && a.get(j) > cur) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, cur);
        }
        for (Integer x : a) 
            System.out.print(x + " ");
        
    }
    
    public static void bubbleSort(ArrayList<Integer> a) {
        int n = a.size();
        
        for (int i = 1; i < n - 1; ++i) 
            for (int j = i + 1; j < n; ++j) 
                if (a.get(i) > a.get(j)) {
                    int tmp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, tmp);
                }
            
        for (Integer x : a) 
            System.out.print(x + " ");
    }
    
    public static void selectionSort(ArrayList<Integer> a) {
        int n = a.size();
        
        for (int i = 0; i < n - 1; i++) {
            int minId = i;
            for (int j = i + 1; j < n; j++) {
                if (a.get(j) < a.get(minId)) 
                    minId = j;
            }
            int tmp = a.get(minId);
            a.set(minId, a.get(i));
            a.set(i, tmp);
        }
        for (Integer x : a) 
            System.out.print(x + " ");
    }
    
    public static void quickSort(ArrayList<Integer> a) {
        quickSortHigh(a, 0, a.size() - 1);
        //quickSortLow(a, 0, a.size() - 1);
        //quickSortMid(a, 0, a.size() - 1);
        //quickSortRandom(a, 0, a.size() - 1);
        
        System.out.println(a);
    }

    public static int partitionHigh(ArrayList<Integer> a, int L, int R) {
        int pivot = a.get(R);
        int i = L - 1;
        for (int j = L; j < R; j++) {
            if (a.get(j) < pivot) {
                i++;
                Collections.swap(a, i, j);
            }
        }
        Collections.swap(a, i + 1, R);
        return (i + 1);
    }
    
    public static void quickSortHigh(ArrayList<Integer> a, int L, int R) {
        if (L >= R) return;
        int pivot = partitionHigh(a, L, R);
        quickSortHigh(a, L, pivot - 1);
        quickSortHigh(a, pivot + 1, R);
    }
    
    public static int partitionLow(ArrayList<Integer> a, int L, int R) {
        int pivot = a.get(L);
        int i = R;
        for (int j = R; j> L; j--) {
            if (a.get(j) > pivot) {
                i--;
                Collections.swap(a, i, j);
            }
        }
        Collections.swap(a, i - 1, L);
        return (i + 1);
    }
    
    public static void quickSortLow(ArrayList<Integer> a, int L, int R) {
        if (L >= R) return;
        int pivot = partitionHigh(a, L, R);
        quickSortLow(a, L, pivot - 1);
        quickSortLow(a, pivot + 1, R);
    }
    
    public static int partitionMid(ArrayList<Integer> a, int L, int R) {
        int i = L, j = R;
        int pivot = a.get((L + R) / 2);
        while (i <= j) {
            while (a.get(i) < pivot) i++;
            while (a.get(j) > pivot) j--;
            if (i <= j) {
                Collections.swap(a, i, j);
                i++;
                j--;
            }
        };
        return i;
    }
    
    public static void quickSortMid(ArrayList<Integer> a, int L, int R) {
        if (L >= R) return;
        int pivot = partitionMid(a, L, R);
        if (L < pivot - 1) quickSortMid(a, L, pivot - 1);
        if (pivot < R) quickSortMid(a, pivot, R);
    }
    
    static void random(ArrayList<Integer> a, int L, int R) {
        Random rand = new Random();
        int pivot = rand.nextInt(R - L + 1) + L;
        Collections.swap(a, pivot, R);
    }
     
    static int partitionRandom(ArrayList<Integer> a, int L, int R) {
        random(a, L, R);
        int pivot = a.get(R);
        int i = (L - 1); 
        for (int j = L; j < R; j++) 
            if (a.get(j) < pivot) {
                i++;
                Collections.swap(a, i, j);    
            }
        
        Collections.swap(a, i + 1, R);
        return i + 1;
    }
 
    public static void quickSortRandom(ArrayList<Integer> a, int L, int R) {
        if (L >= R) return;
        int pivot = partitionRandom(a, L, R);
        quickSortRandom(a, L, pivot - 1);
        quickSortRandom(a, pivot + 1, R);
    }
    
}
