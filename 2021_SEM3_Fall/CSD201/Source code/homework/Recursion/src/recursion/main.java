package recursion;

import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class main {

    public static void main(String[] args) {
        int n = 3; 
        rHanoiTower(n, 'A', 'B', 'C'); // move from A to B use C
    }

    static int factorialRecursion(int n) {
        if (n == 1) return 1;
        return n * factorialRecursion(n - 1);
    }

    static int factorialLoop(int n) {
        int i;
        for (i = 1; i <= n; i++) {
            i *= i;
        }
        return i;
    }

    static int factorialStack(int n) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        for (int i = 1; i <= n; ++i) {
            st.push(st.pop() * i);
        }
        return st.peek();
    }

    static int rFibo(int n) {
        if (n <= 1) return n;
        return rFibo(n - 1) + rFibo(n - 2);
    }
    
    static int iFibo(int n) {
        if (n <= 1) return n;
        int f1 = 0, f2 = 1;
        int fn = 0;
        for (int i = 2; i <= n; ++i) {
            fn = f1 + f2;
            f2 = f1;
            f1 = fn;
        }
        return fn;
    }
    
    static int rGcd(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        if (b == 0) return a;
        if (a < b) return rGcd(b - a, a);
        else if (a > b) return rGcd(a - b, a);
        else return a;
    }
   
    static int iGcd(int a, int b) {
        a = (a > 0) ? a : -a;
        b = (b > 0) ? b : -b;
        while (a != b) {
            if (a > b) a -= b;
            else b -= a;
        }
        return a;
    }
    
    static boolean rPalindrome(String s, int l, int r) {
        if (l >= r)  return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        if (l < r + 1) {
            return rPalindrome(s, l + 1, r - 1);
        }
        return true;
    }

    static boolean iPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (r > l) {
            if (s.charAt(l) != s.charAt(r)) return false;
            ++l; --r;
        }
        return true;
    }
    
    static void rHanoiTower(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move 1 from " + A + " to " + B);
            return;
        }
        rHanoiTower(n - 1, A, C, B);
        System.out.println("Move " + n + " from " + A + " to " + B);
        rHanoiTower(n - 1, C, B, A);
    }
}
