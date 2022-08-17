package usingstack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 * @param <T>
 */
public class MyStack<T extends Comparable<T>> {
    SLL<T> st = new SLL<>();
    
    void clear() {
        st.removeAll();
    }
    
    boolean isEmpty() {
        return st.isEmpty();
    }
    
    void push(T el) {
        st.addLast(el);
    }
    
    T pop() throws Exception {
        T el = st.get(st.size() - 1);
        st.removeLast();
        return el;
    }
    
    T peek() throws Exception {
        if (isEmpty()) return null;
        return st.get(st.size() - 1);
    }
    
    void printAll() {
        st.printAll();
    }
}
