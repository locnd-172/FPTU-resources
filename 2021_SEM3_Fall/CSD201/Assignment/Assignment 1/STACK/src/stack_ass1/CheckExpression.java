// Validate Expression using STACK

package stack_ass1;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class CheckExpression {
    
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        
        Stack<Character> st = new Stack<>();
        boolean res = false;
        
        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') 
                st.push(ch);
            else if (ch == ')' || ch == '}' || ch == ']') {
                Character cmp = st.peek();
                if (cmp == '(' && ch == ')') st.pop();
                else if (cmp == '{' && ch == '}') st.pop();
                else if (cmp == '[' && ch == ']') st.pop();
            } else {
                continue;
            }
        }
        if (st.isEmpty() == true) res = true;
        System.out.println(res == false ? "False" : "True");
    }
}
