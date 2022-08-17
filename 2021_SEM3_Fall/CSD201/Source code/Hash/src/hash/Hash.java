package hash;

import com.sun.jdi.Value;
import java.awt.RenderingHints.Key;
import java.util.Arrays;
import java.util.Scanner;
import java.util.WeakHashMap;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Hash {

    static int N;
    static int[] a, hash;

    public static void main(String[] args) {

        a = new int[]{298, 75, 5, 135, 86, 90, 111, 1011, 23, 307};
        N = a.length;
        
        buildHash();
        for (int i = 0; i < N; i++) {
            System.out.println("Key: " + i + ", value: " + getHash(i));
        }
        
    }
    
    public static void buildHash() {
        hash = new int[N];
        Arrays.fill(hash, -1);
        for (int val : a) {
            int key = val % N;
            while (hash[key] != -1) {
                key = (key + 1) % N;
            }
            hash[key] = val;
        }
    }
    
    public static int getHash(int key) {
        return hash[key];
    }
    
}
