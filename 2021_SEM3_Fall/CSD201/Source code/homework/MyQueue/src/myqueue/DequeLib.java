package myqueue;

import singlelinkedlist.SLL;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class DequeLib<E extends Comparable<E>> {
    SLL<E> LDQ = new SLL<>();
    
    public void clear() {
        LDQ.removeAll();
    }
    
    public boolean isEmpty() {
        return LDQ.isEmpty();
    }
    
    public void addFist(E data) {
        LDQ.addFirst(data);
    }
    
    public void addLast(E data) {
        LDQ.addLast(data);
    }
    
     public E removeFirst() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        E data = LDQ.get(0);
        LDQ.removeFirst();
        return data;
    }
    
    public E removeLast() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        E data = LDQ.get(LDQ.size() - 1);
        LDQ.removeLast();
        return data;
    }
    
    public E first() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        return LDQ.get(0);
    } 
    
    public E last() throws Exception {
        if (LDQ.isEmpty()) {
            return null;
        }
        return LDQ.get(LDQ.size() - 1);
    } 
    
    public int size() {
        return LDQ.size();
    }
}
