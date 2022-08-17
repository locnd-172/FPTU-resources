package queue;

import singlelinkedlist.SLL;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class PriorityQueueLib {
    private SLL<Patient> PQ = new SLL<>();
    
    
    public boolean isEmpty() {
        return PQ.isEmpty();
    }
    
    public void clear() {
        PQ.removeAll();
    }
    
    public void enqueue(Patient obj) throws Exception {
        int pri = obj.getPriority();
        int pos = 0;
        if (PQ.isEmpty() || pri > PQ.get(0).getPriority()) {
            PQ.addFirst(obj);
        } else {
            for (int i = 0; i < PQ.size(); ++i) {
                int curPri = PQ.get(i).getPriority();
                if (pri > curPri) {
                    break;
                }
                ++pos;
            }
            if (pos >= PQ.size()) PQ.addLast(obj);
            else PQ.addPos(pos, obj);
        }
    }
    
    public Patient dequeue() throws Exception {
        if (PQ.isEmpty()) {
            return null;
        }
        Patient obj = PQ.get(PQ.size() - 1);
        PQ.removeLast();
        return obj;
    }
    
    public Patient front() throws Exception {
        if (PQ.isEmpty()) {
            return null;
        }
        return PQ.get(0);
    } 
    
    public Patient pop() throws Exception {
        if (PQ.isEmpty()) {
            return null;
        }
        Patient obj = PQ.get(0);
        PQ.removeFirst();
        return obj;
    }
    
    public int size() {
        return PQ.size();
    }
}