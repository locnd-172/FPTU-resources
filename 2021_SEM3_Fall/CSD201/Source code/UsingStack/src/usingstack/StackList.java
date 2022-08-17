package usingstack;

import java.util.LinkedList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 * @param <T>
 */
public class StackList<T extends Comparable<T>> {
    LinkedList<T> st = new LinkedList<>();
    
    void clear() {
        st.removeAll(st);
    }
    
    boolean isEmpty() {
        return st.isEmpty();
    }
    
    void push(T el) {
        st.addLast(el);
    }
    
    T pop() {
        T el = st.getLast();
        st.removeLast();
        return el;
    }
    
    T peek() {
        if (isEmpty()) return null;
        return st.getLast();
    }
    
    void printAll() {
        System.out.println(st.toString());
    }
}
