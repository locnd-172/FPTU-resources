package usingstack;

import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class UsingStack {

    public static void main(String[] args) throws Exception {
        // USING STACK IN Java.util
        /*Stack<Integer> st = new Stack<>();
        st.push(10);
        st.push(15);
        st.push(7);
        
        System.out.println(st.pop());
        System.out.println(st.peek());*/
        
        
        // USING SELF IMPLEMENTED STACK WITH SLL
        MyStack<String> mst = new MyStack<>();
        
        mst.push("hello");
        mst.push("world");
        mst.push("code");
        
        System.out.println(mst.peek());
        mst.pop();
        System.out.println(mst.peek());
        mst.printAll();
        
        System.out.println("");
        
        mst.clear();
        mst.printAll();
        
         // USING SELF IMPLEMENTED STACK WITH LINKEDLIST IN Java.uil
         StackList<String> stl = new StackList<>();
         stl.push("code");
         stl.push("coffee");
         stl.push("hello");
         
         stl.printAll();
         
         System.out.println(stl.peek());
         stl.pop();
         System.out.println(stl.peek());
         
    }
    
}
