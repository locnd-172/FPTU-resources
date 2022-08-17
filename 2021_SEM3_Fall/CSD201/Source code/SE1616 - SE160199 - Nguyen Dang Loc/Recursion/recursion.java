package recursion;

import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class recursion {

    public static void main(String[] args) {
        
        // factorial
        int nFac = 5;
        System.out.println("Factorial of " + nFac + ": ");
        System.out.println(factorialLoop(nFac));
        System.out.println(factorialStack(nFac));
        System.out.println(factorialRecursion(nFac));
        System.out.println("");
        
        // fibonacci: 0 1 1 2 3 5 8 13 21 ...
        int nFibo = 8;
        System.out.println(nFibo + "th fibonacci: ");
        System.out.println(recursiveFibo(nFibo));
        System.out.println(iterateFibo(nFibo));
        System.out.println("");
        
        // GCD
        System.out.println("GCD(48, 60): ");
        System.out.println(recursiveGCD(48, 60));
        System.out.println(iterateGCD(48, 60));
        System.out.println("");
        
        // Palindrome
        String s = "aabbaa";
        System.out.println("Check palindrome of " + s + ": ");
        System.out.println(recursivePalindrome(s, 0, s.length() - 1));
        System.out.println(iteratePalindrome(s));
        System.out.println("");
        
        // Hanoi tower 
        int n = 3; 
        System.out.println("Move " + n + " disks from A to B using C as auxiliary disk");
        System.out.println("Recursion:");
        recursiveHanoiTower(n, 'A', 'B', 'C'); 
        System.out.println("\nStack:");
        stackHanoiTower(n);
        
    }

    static int factorialRecursion(int n) {
        if (n == 0) return 1;
        return n * factorialRecursion(n - 1);
    }

    static int factorialLoop(int n) {
        int p = 1;
        for (int i = 1; i <= n; i++) {
            p *= i;
        }
        return p;
    }

    static int factorialStack(int n) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        for (int i = 1; i <= n; ++i) {
            st.push(st.pop() * i);
        }
        return st.peek();
    }

    static int recursiveFibo(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        return recursiveFibo(n - 1) + recursiveFibo(n - 2);
    }
    
    static int iterateFibo(int n) {
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
    
    static int recursiveGCD(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        if (b == 0) return a;
        if (a < b) return recursiveGCD(b - a, a);
        else if (a > b) return recursiveGCD(a - b, a);
        else return a;
    }
   
    static int iterateGCD(int a, int b) {
        a = (a > 0) ? a : -a;
        b = (b > 0) ? b : -b;
        while (a != b) {
            if (a > b) a -= b;
            else b -= a;
        }
        return a;
    }
    
    static boolean recursivePalindrome(String s, int l, int r) {
        if (l >= r)  return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        if (l < r + 1) {
            return recursivePalindrome(s, l + 1, r - 1);
        }
        return true;
    }

    static boolean iteratePalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (r > l) {
            if (s.charAt(l) != s.charAt(r)) return false;
            ++l; --r;
        }
        return true;
    }
    
    static void recursiveHanoiTower(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move 1 from " + A + " to " + B);
            return;
        }
        recursiveHanoiTower(n - 1, A, C, B);
        System.out.println("Move " + n + " from " + A + " to " + B);
        recursiveHanoiTower(n - 1, C, B, A);
    }
    
    static void stackHanoiTower(int n) {
        Stack<Character> A = new Stack<>();
        Stack<Character> B = new Stack<>();
        Stack<Character> C = new Stack<>();
        Stack<Integer> count = new Stack<>();

        char a = 'A', b = 'B', c = 'C';

        count.add(n);
        A.add(a);
        B.add(b);
        C.add(c);

        while (!count.empty()) {

            int x = count.pop();
            char cura = A.pop();
            char curb = B.pop();
            char curc = C.pop();

            if (x == 1) {
                System.out.println(cura + " to " + curb);
                continue;
            }
            count.add(x - 1);
            A.add(curc);
            B.add(curb);
            C.add(cura);

            count.add(1);
            A.add(cura);
            B.add(curb);
            C.add(curc);

            count.add(x - 1);
            A.add(cura);
            B.add(curc);
            C.add(curb);
        }
    }
}
