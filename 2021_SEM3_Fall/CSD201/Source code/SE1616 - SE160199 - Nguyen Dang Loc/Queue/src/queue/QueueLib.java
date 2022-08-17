package queue;

import singlelinkedlist.SLL;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class QueueLib<E extends Comparable<E>> {
    SLL<E> LQ = new SLL<>();
    
    public void clear() {
        LQ.removeAll();
    }
    
    public boolean isEmpty() {
        return LQ.isEmpty();
    }
    
    public void enqueue(E data) {
        LQ.addFirst(data);
    }
    
    public E dequeue() throws Exception {
        if (LQ.isEmpty()) {
            return null;
        }
        E data = LQ.get(LQ.size() - 1);
        LQ.removeLast();
        return data;
    }
    
    public E front() throws Exception {
        if (LQ.isEmpty()) {
            return null;
        }
        return LQ.get(0);
    } 
    
    public int size() {
        return LQ.size();
    }
}
