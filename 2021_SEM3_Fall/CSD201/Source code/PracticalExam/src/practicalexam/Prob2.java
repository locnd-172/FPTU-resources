package practicalexam;

import java.util.*;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Prob2 {

    public Prob2() {
    }
    
    private static final Scanner sc = new Scanner(System.in);
    int n, r, g, b;
    int[][] dp = new int[110][1 << 3];

    Vector<Integer> red = new Vector();
    Vector<Integer> green = new Vector();
    Vector<Integer> blue = new Vector();

    public void input() {
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            r = sc.nextInt();
            g = sc.nextInt();
            b = sc.nextInt();
            red.add(r);
            green.add(g);
            blue.add(b);
        }
    }

    public void minOperations() {
        int i, j;
        
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= 7; j++) {
                dp[i][j] = (1<<30);
            }
        }
        
        dp[0][0] = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j <= 7; j++) {
                dp[i + 1][j | 1] = Math.min(dp[i + 1][j | 1], 
                                            dp[i][j] + green.get(i) + blue.get(i));
                
                dp[i + 1][j | 2] = Math.min(dp[i + 1][j | 2], 
                                            dp[i][j] + red.get(i) + blue.get(i));
                
                dp[i + 1][j | 4] = Math.min(dp[i + 1][j | 4], 
                                            dp[i][j] + red.get(i) + green.get(i));
            }
        }
        j = 0;
        for (i = 0; i < n; i++) {
            if (green.get(i) != 0) j |= 1;
            if (red.get(i) != 0) j |= 2;
            if (blue.get(i) != 0)j |= 4;
        }
        if (dp[n][j] >= (1 << 30)) dp[n][j] = -1;
        
        System.out.println(dp[n][j]);
    }

    public void solve() {
        input();
        minOperations();
    }
}

