package stackexercise2;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
import java.util.Scanner;
import java.util.Stack;

public class StackExercise2 {
    public static void main(String[] args) {
        System.out.println("Enter A & B below");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("A: "); String num1 = sc.nextLine();
        System.out.print("B: "); String num2 = sc.nextLine();
        
        int Case = 0;
        if (num1.charAt(0) != '-' && num2.charAt(0) != '-') Case = 1;
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') Case = 2;
        if (num1.charAt(0) != '-' && num2.charAt(0) == '-') Case = 3;
        if (num1.charAt(0) == '-' && num2.charAt(0) != '-') Case = 4;
        
        Addition(Case, num1, num2);
        Subtraction(Case, num1, num2);
        Multiplication(Case, num1, num2);
        Division(Case, num1, num2);
        
    }
    
    public static void Addition(int Case, String num1, String num2) {
        Stack<Integer> sum;
        String num1_ = "", num2_ = "";
        switch (Case) {
            case 1 -> {
                sum = plus(toStack(num1), toStack(num2));
                System.out.print("A + B = "); display(sum);
                return;
            }
            case 2 -> {
                sum = plus(toStack(num1.substring(1)), toStack(num2.substring(1)));
                System.out.print("A + B = -"); display(sum);
                return;
            }
            case 3 -> { num1_ = num1; num2_ = num2.substring(1); }
            case 4 -> {
                num1_ = num1.substring(1);
                num2_ = num2;
                // swap
                num1_ = num1_ + num2_;
                num2_ = num1_.substring(0, num1_.length() - num2_.length());
                num1_ = num1_.substring(num2_.length());
            }
            default -> {
            }
        }
        int cmp = compare(toStack(num1_), toStack(num2_));
        if (cmp < 0) {
            sum = minus(toStack(num2_), toStack(num1_));
            System.out.print("A + B = -"); display(sum);
        } else {
            sum = minus(toStack(num1_), toStack(num2_));
            System.out.print("A + B = "); display(sum);
        }
    }
    
    public static void Subtraction(int Case, String num1, String num2) {
        Stack<Integer> dif;
        String num1_ = "", num2_ = "";
        switch (Case) {
            case 1 -> { num1_ = num1; num2_ = num2; }
            case 2 -> { num1_ = num1.substring(1); num2_ = num2.substring(1); }
            case 3 -> { 
                num1_ = num1;
                num2_ = num2.substring(1);
                dif = plus(toStack(num1_), toStack(num2_));
                System.out.print("A - B = "); display(dif);
                return;
            }
            case 4 -> {
                num1_ = num1.substring(1);
                num2_ = num2;
                dif = plus(toStack(num1_), toStack(num2_));
                System.out.print("A - B = -"); display(dif);
                return;
            }
            default -> {}
        }
        int cmp = compare(toStack(num1_), toStack(num2_));
        if (cmp < 0) {
            dif = minus(toStack(num2_), toStack(num1_));
        } else {
            dif = minus(toStack(num1_), toStack(num2_));
        }
        
        System.out.print("A - B = "); 
        if (Case != 2) System.out.print(cmp < 0 ? "-" : "");
        else System.out.print(cmp <= 0 ? "" : "-");
        display(dif);
    }
    
    public static void Multiplication(int Case, String num1, String num2) {
        String num1_ = "", num2_ = "";
        switch (Case) {
            case 1 -> { num1_ = num1; num2_ = num2; }
            case 2 -> { num1_ = num1.substring(1); num2_ = num2.substring(1); }
            case 3 -> { num1_ = num1; num2_ = num2.substring(1); }
            case 4 -> { num1_ = num1.substring(1); num2_ = num2; }
            default -> {}
        }
        
        var prod = mul(toStack(num2_), toStack(num1_));
        System.out.print("A x B = "); 
        if (Case == 3 || Case == 4) System.out.print("-");
        display(prod);
    }
    
    public static void Division(int Case, String num1, String num2) {
        String num1_ = "", num2_ = "";
        switch (Case) {
            case 1 -> { num1_ = num1; num2_ = num2; }
            case 2 -> { num1_ = num1.substring(1); num2_ = num2.substring(1); }
            case 3 -> { num1_ = num1; num2_ = num2.substring(1); }
            case 4 -> { num1_ = num1.substring(1); num2_ = num2; }
            default -> {}
        }
        
        int cmp = compare(toStack(num1_), toStack(num2_));
        if (cmp < 0) System.out.println("A / B = 0");
        else {
            int cmp2 = compare(toStack(num2_), toStack(String.valueOf(Long.MAX_VALUE)));
            if (cmp2 < 0) {
                Long B = Long.parseLong(num2_);
                Stack<Integer> div = longDiv(toStack(num1_), B);
                
                System.out.print("A / B = "); 
                if (Case == 3 || Case == 4) System.out.print("-");
                display(div);
            }
            else {
                Stack<Integer> div = stackDiv(toStack(num1_), toStack(num2_));
                
                System.out.print("A / B = "); 
                if (Case == 3 || Case == 4) System.out.print("-");
                display(div);
            }
        }
    }
    
    public static int compare(Stack<Integer> a, Stack<Integer> b) {
        a = removeLeadingZero(a);
        b = removeLeadingZero(b);
        if (a.size() < b.size()) return -1;
        else if (a.size() > b.size()) return 1;
        else {
            for (int i = 0; i < a.size(); ++i) {
                if (a.get(i) < b.get(i)) return -1;
                else if (a.get(i) > b.get(i)) return 1;
            }
            return 0;
        }
    }
    
    public static Stack<Integer> stackDiv(Stack<Integer> dividend, Stack<Integer> divisor) {
        Stack<Integer> div = new Stack<>();
        div.push(0);
        int cnt = 0;
        while (true) {
            int cmp = compare(dividend, divisor);
            if (cmp == -1) break;
            else if (cmp == 0) {
                div = add(div, toStack("1")); break;
            } else {
                div = add(div, toStack("1"));
                dividend = minus(dividend, divisor);
                ++cnt;
            }
        }
        System.out.println(cnt);
        return div;        
    }
    
    public static Stack<Integer> longDiv(Stack<Integer> dividend, long divisor) {
        Stack<Integer> res = new Stack<>();
        Stack<Integer> tmp = new Stack<>();
        int carry = 0;
        
        while (!dividend.empty()) {
            tmp.push(dividend.peek());
            dividend.pop();
        }
        dividend = tmp;
        while (!tmp.empty()) {
            int x = carry * 10 + tmp.peek();
            res.push(x / (int)divisor);
            carry = x % (int)divisor;
            tmp.pop();
        }
        res = removeLeadingZero(res);
        return res;
    }
    
    public static Stack<Integer> removeLeadingZero(Stack<Integer> num) {
        Stack<Integer> reverseNum = new Stack<>();
        while (!num.empty()) {
            reverseNum.push(num.peek());
            num.pop();
        }
        while (!reverseNum.empty() && reverseNum.peek() == 0) reverseNum.pop();
        while (!reverseNum.empty()) {
            num.push(reverseNum.peek());
            reverseNum.pop();
        }
        return num;
    }
    
    public static Stack<Integer> toStack(String num) {
        Stack<Integer> tmp = new Stack<>();
        for (int i = 0; i < num.length(); ++i) {
            int d = num.charAt(i) - '0';
            tmp.push(d);
        }
        return tmp;
    }

    public static Stack<Integer> add(Stack<Integer> A, Stack<Integer> B) {
        Stack<Integer> N1 = new Stack<>();
        for (int i = 0; i < A.size(); ++i) {
            N1.push(A.get(i));
        }
        Stack<Integer> N2 = new Stack<>();
        for (int i = 0; i < B.size(); ++i) {
            N2.push(B.get(i));
        }
        
        Stack<Integer> res = new Stack<Integer>();
        int sum = 0, rem = 0;
        while (!N1.isEmpty() && !N2.isEmpty()) {
            sum = (rem + N1.peek() + N2.peek());
            res.add(sum % 10);
            rem = sum / 10;
            N1.pop();
            N2.pop();
        }
        while (!N1.isEmpty()) {
            sum = (rem + N1.peek());
            res.add(sum % 10);
            rem = sum / 10;
            N1.pop();
        }

        while (!N2.isEmpty()) {
            sum = (rem + N2.peek());
            res.add(sum % 10);
            rem = sum / 10;
            N2.pop();
        }

        while (rem > 0) {
            res.add(rem);
            rem /= 10;
        }

        while (!res.isEmpty()) {
            N1.add(res.peek());
            res.pop();
        }
        res = N1;
        return res;
    }

    public static Stack<Integer> plus(Stack<Integer> A, Stack<Integer> B) {
        Stack<Integer> N1 = new Stack<>();
        Stack<Integer> N2 = new Stack<>();
        
        // normalization
        int sz1 = A.size(), sz2 = B.size();
        int sz = Math.max(sz1, sz2);
        
        while (sz1 < sz) { N1.push(0); ++sz1; }
        while (sz2 < sz) { N2.push(0); ++sz2; }
        
        for (int i = 0; i < A.size(); ++i) {
            N1.push(A.get(i));
        }
        for (int i = 0; i < B.size(); ++i) {
            N2.push(B.get(i));
        }
        
        // calculate
        int carry = 0;
        Stack<Integer> res = new Stack<>();
        while (!N1.isEmpty()) {
            int tmp = N1.peek() + N2.peek() + carry;
            carry = tmp / 10;
            tmp = tmp % 10;
            res.push(tmp);
            N1.pop(); N2.pop();
        }
        if (carry > 0) {
            res.push(1);
        }
        // reverse stack
        while (!res.empty()) {
            N1.push(res.peek());
            res.pop();
        }
        res = N1;
        
        return res;
    }
    
    public static Stack<Integer> minus(Stack<Integer> A, Stack<Integer> B) {
        Stack<Integer> N1 = new Stack<>();
        Stack<Integer> N2 = new Stack<>();
        
        // normalization
        int sz1 = A.size(), sz2 = B.size();
        int sz = Math.max(sz1, sz2);
        
        while (sz1 < sz) { N1.push(0); ++sz1; }
        while (sz2 < sz) { N2.push(0); ++sz2; }
        
        for (int i = 0; i < A.size(); ++i) {
            N1.push(A.get(i));
        }
        for (int i = 0; i < B.size(); ++i) {
            N2.push(B.get(i));
        }
       
        // calculate
        int carry = 0;
        Stack<Integer> res = new Stack<>();
        while (!N1.isEmpty()) {
            int n2;
            if (N2.isEmpty()) n2 = 0;
            else n2 = N2.peek();
            int tmp = N1.peek() - n2 - carry;
            if (tmp < 0) {
                tmp += 10;
                carry = 1;
            } else carry = 0;
            N1.pop(); 
            if (!N2.isEmpty()) N2.pop();
            res.push(tmp%10);
        }
        
        // reverse stack
        while (!res.empty()) {
            N1.push(res.peek());
            res.pop();
        }
        res = N1;
        res = removeLeadingZero(res);
        return res;
    }
    
    public static Stack<Integer> mul(Stack<Integer> A, Stack<Integer> B) {
        int carry = 0, cnt = 0;
        Stack<Integer> res = new Stack<>();
        Stack<Integer> n1 = new Stack<>();
        res.push(0);
        
        while (!B.isEmpty()) {
            int d2 = B.peek();
            
            Stack<Integer> tmp = new Stack<>();     // assign A to n1
            for (int i = 0; i < A.size(); ++i) {
                n1.push(A.get(i));
            }
            
            carry = 0;
            while (!n1.isEmpty()) {
                int d1 = n1.peek();
                int p = d1 * d2 + carry;
                carry = p / 10;
                tmp.push(p % 10);
                n1.pop();
            }
            if (carry > 0) tmp.push(carry);
            
            Stack<Integer> cv = new Stack<>();      // convert temp sum
            while (!tmp.empty()) {
                cv.push(tmp.peek());
                tmp.pop();
            }
            for (int i = 0; i < cnt; ++i) 
                cv.push(0);                         // mul 10

            res = add(res, cv);
            ++cnt;
            B.pop();
        }
        res = removeLeadingZero(res);
        return res;
    }
    
    public static void display(Stack<Integer> num) {
        if (num.empty()) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < num.size(); ++i) {
            System.out.print(num.get(i));
        }
        System.out.println();
    }
    
}
