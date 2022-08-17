package kmp;

import java.util.ArrayList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class KMP {

    static int[] nxt;
    
    static void MP(String str, String pat) {
        int n = str.length();
        int m = pat.length();
        nxt = new int[m];
        
        int j = nxt[0] = 0;
        for(int i = 1; i < m; ++i) {
            while (j > 0 && pat.charAt(j) != pat.charAt(i)) j = nxt[j - 1];
            nxt[i] = ++j; 
        }
        j = 0;
        ArrayList<Integer> id = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            while (j > 0 && pat.charAt(j) != str.charAt(i))  j = nxt[j - 1];
            j++;
            if (j >= m) {
                id.add(i - j + 1);
                j = nxt[j - 1];
            }
        }
        for (int i : nxt) {
            System.out.print(i + " ");
        }
        System.out.println("");
        if (id.isEmpty()) System.out.println("No match");
        else {
            System.out.println("Matching index:");
            for (Integer x : id) 
                System.out.print(x + " ");
        }
    }
    
    static void KMP(String str, String pat) {
        int n = str.length();
        int m = pat.length();
        nxt = new int[m];
        
        int j = nxt[0] = 0;
        for(int i = 1; i < m; ++i) {
            while (j > 0 && pat.charAt(j) != pat.charAt(i)) j = nxt[j - 1];
            if (pat.charAt(j) == pat.charAt(i)) ++j;
            nxt[i] = j;
        }
        
        j = 0;
        ArrayList<Integer> id = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            while (j > 0 && pat.charAt(j) != str.charAt(i))  j = nxt[j - 1];
            if (pat.charAt(j) == str.charAt(i)) ++j;
            if (j >= m) {
                id.add(i - j + 1);
                j = nxt[j - 1];
            }
        }
        if (id.isEmpty()) System.out.println("No match");
        else {
            System.out.println("Matching index:");
            for (Integer x : id) 
                System.out.print(x + " ");
        }
    }
    
    public static void main(String[] args) {
        String str = "abcaaabcbabcaab";
        String pat = "ababaca";
        
        KMP(str, pat);
        System.out.println("");
        MP(str, pat);
        
        System.out.println("");
    }
   
}
