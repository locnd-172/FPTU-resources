package radixsort;

import java.util.Arrays;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class RadixSort {

    static void coutingSort(int a[], int base) {
        int n = a.length;
        int out[] = new int[n]; 
        int cnt[] = new int[10];
        Arrays.fill(cnt, 0);

        for (int i = 0; i < n; i++) {
            int d = (a[i] / base) % 10;
            cnt[d]++;
        }
        for (int i = 1; i < 10; i++) cnt[i] += cnt[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / base) % 10;
            out[cnt[d] - 1] = a[i];
            cnt[d]--;
        }

        for (int i = 0; i < n; i++) a[i] = out[i];
    }

    static void radixSort(int a[]) {
        int mx = a[0];
        for (int x : a) 
            mx = Math.max(mx, x);

        for (int b = 1; mx / b > 0; b *= 10) 
            coutingSort(a, b);
    }


    public static void main(String[] args) {
        int a[] = {170, 45, 75, 90, 802, 24, 2, 66};

        radixSort(a);
        System.out.println(Arrays.toString(a));
    }
}
