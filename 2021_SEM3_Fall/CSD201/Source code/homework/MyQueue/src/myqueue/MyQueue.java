package myqueue;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class MyQueue {

    public static void main(String[] args) throws Exception {
//        QueueLib<Integer> Q = new QueueLib<>();
//        Q.enqueue(5);
//        Q.enqueue(7);
//        Q.enqueue(9);
//        System.out.println(Q.dequeue());
//        System.out.println(Q.front());
    
        PriorityQueueLib PQ = new PriorityQueueLib();
        PQ.enqueue(new Patient(15, "Loc"));
        PQ.enqueue(new Patient(8, "Van"));
        PQ.enqueue(new Patient(20, "May"));
        PQ.enqueue(new Patient(6, "Thom"));
        PQ.enqueue(new Patient(14, "Ca"));
        PQ.enqueue(new Patient(11, "Rot"));
        PQ.enqueue(new Patient(18, "Sua"));
        while (!PQ.isEmpty()) {
            System.out.println(PQ.pop());
        }
        
    }

}
