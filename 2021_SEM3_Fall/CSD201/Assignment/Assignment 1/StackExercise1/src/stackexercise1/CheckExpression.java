// Validate Expression using STACK

package stackexercise1;

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
        
        System.out.println(check(input));
    }
    
    public static String check(String input) { 
        if(input == null || input.length() % 2 != 0) return "NO";
        
        Stack<Character> st = new Stack<Character>();
        
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else if(c == ')' || c == '}' || c == ']') {
                if(!st.isEmpty()){
                    char cmp = st.pop();
                    if(cmp == '(' && c != ')') return "NO";
                    if(cmp == '{' && c != '}') return "NO";
                    if(cmp == '[' && c != ']') return "NO";
                } else 
                    return "NO";
            }
        }
    
        return (st.isEmpty() == true ? "YES" : "NO");
    }
}
