package stringmatching;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class StringMatching {

    public static void main(String[] args) {
        String s1 = "abcaaabababcaab";
        String s2 = "abc";
        
        System.out.print("matching index: ");
        for (int i = 0; i < s1.length() - s2.length(); i++) {
            int t = i;
            int j = 0;
            while (t < s1.length() && j < s2.length()) {
                if (s1.charAt(t) != s2.charAt(j)) break;
                ++t; ++j; 
            }
            if (j == s2.length()) System.out.print(i + " ");
        }
    }
}
