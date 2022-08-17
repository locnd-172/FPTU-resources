package bucketsort;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class BucketSort {

    static void bucketSort(float a[]) {
        int n = a.length;
        if (n <= 0) return;

        ArrayList<ArrayList<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) 
            buckets.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            float bkID = a[i] * n;
            buckets.get((int) bkID).add(a[i]);
        }

        for (int i = 0; i < n; i++) 
            Collections.sort(buckets.get(i));

        int sz = 0;
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < buckets.get(i).size(); j++) 
                a[sz++] = buckets.get(i).get(j);
    }

    public static void main(String[] args) {
        float a[] = { (float)1.17, (float)0.59,
                      (float)0.65, (float)0.14,
                      (float)0.75, (float)0.4};
        bucketSort(a);
        for (float x : a) 
            System.out.print(x + " ");
        
    }
}
