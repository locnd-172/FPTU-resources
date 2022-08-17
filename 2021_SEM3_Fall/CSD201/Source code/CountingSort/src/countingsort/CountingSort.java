package countingsort;

import java.util.Arrays;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class CountingSort {

    static void coutingSort(int a[], int max) {
        int n = a.length;
        int out[] = new int[n];
        int cnt[] = new int[max + 1];
        Arrays.fill(cnt, 0);

        for (int i = 0; i < n; ++i) ++cnt[a[i]];
        for (int i = 1; i <= max; ++i) cnt[i] += cnt[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            out[cnt[a[i]] - 1] = a[i];
            --cnt[a[i]];
        }

        for (int i = 0; i < n; ++i) a[i] = out[i];
    }

    public static void main(String[] args) {
        
        int[] a = {1, 4, 5, 2, 1, 2, 5, 6, 9, 4, 2};
        int max = -1;
        for (int x : a) 
            max = Math.max(max, x);
        
        coutingSort(a, max);
        
        for (int i : a) 
            System.out.print(i + " ");
    }
}
