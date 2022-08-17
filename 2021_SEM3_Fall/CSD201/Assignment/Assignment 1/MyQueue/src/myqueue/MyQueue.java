package myqueue;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class MyQueue {

    public static void main(String[] args) throws Exception {
        queueExecution();
        priorityQueueExecution();
        
    }

    public static void queueExecution() throws Exception {
        QueueLib<Integer> Q = new QueueLib<>();
        Q.enqueue(5);
        Q.enqueue(7);
        Q.enqueue(9);
        System.out.println(Q.dequeue());
        System.out.println(Q.front());
    }

    public static void priorityQueueExecution() throws Exception {
        PriorityQueueLib PQ = new PriorityQueueLib();
        PQ.enqueue(new Patient(15, "Nam"));
        PQ.enqueue(new Patient(80, "Toan"));
        PQ.enqueue(new Patient(20, "Duc"));
        PQ.enqueue(new Patient(15, "Hoang"));
        PQ.enqueue(new Patient(14, "Van"));
        PQ.enqueue(new Patient(11, "Trung"));
        PQ.enqueue(new Patient(18, "Binh"));
        System.out.println("Front: " + PQ.front());
        System.out.println("");
        while (!PQ.isEmpty()) {
            System.out.println(PQ.pop());
        }
    }

    public static void dequeueExecution() {
        
    }
}
