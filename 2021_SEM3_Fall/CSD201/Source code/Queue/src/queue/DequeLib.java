package queue;

import singlelinkedlist.SLL;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class DequeLib<T extends Comparable<T>> {
    SLL<T> LDQ = new SLL<>();
    
    public void clear() {
        LDQ.removeAll();
    }
    
    public boolean isEmpty() {
        return LDQ.isEmpty();
    }
    
    public void addFirst(T data) {
        LDQ.addFirst(data);
    }
    
    public void addLast(T data) {
        LDQ.addLast(data);
    }
    
     public T removeFirst() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        T data = LDQ.get(0);
        LDQ.removeFirst();
        return data;
    }
    
    public T removeLast() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        T data = LDQ.get(LDQ.size() - 1);
        LDQ.removeLast();
        return data;
    }
    
    public T first() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        return LDQ.get(0);
    } 
    
    public T last() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        return LDQ.get(LDQ.size() - 1);
    } 
    
    public int size() {
        return LDQ.size();
    }
}
