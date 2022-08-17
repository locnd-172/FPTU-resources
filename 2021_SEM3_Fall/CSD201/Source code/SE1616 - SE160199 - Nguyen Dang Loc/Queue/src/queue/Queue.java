package queue;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Queue {

    public static void main(String[] args) throws Exception {
        queueExecution();
        priorityQueueExecution();
        dequeueExecution();
    }

    public static void queueExecution() throws Exception {
        System.out.println("QUEUE");
        QueueLib<Integer> Q = new QueueLib<>();
        Q.enqueue(5);
        Q.enqueue(7);
        Q.enqueue(9);
        System.out.println(Q.dequeue());
        System.out.println(Q.front());
        System.out.println("");
    }

    public static void priorityQueueExecution() throws Exception {
        System.out.println("PRIORITY QUEUE");
        PriorityQueueLib PQ = new PriorityQueueLib();
        PQ.enqueue(new Patient(15, "Nam"));
        PQ.enqueue(new Patient(80, "Toan"));
        PQ.enqueue(new Patient(20, "Duc"));
        PQ.enqueue(new Patient(15, "Hoang"));
        PQ.enqueue(new Patient(14, "Van"));
        PQ.enqueue(new Patient(11, "Trung"));
        PQ.enqueue(new Patient(18, "Binh"));
        System.out.println("Front: " + PQ.front());
        System.out.println("Tail: " + PQ.dequeue());
        System.out.println("In queue: ");
        while (!PQ.isEmpty()) {
            System.out.println(PQ.pop());
        }
        System.out.println("");
    }

    public static void dequeueExecution() throws Exception {
        System.out.println("DEQUE");
        DequeLib<Integer> DQ = new DequeLib<>();
        DQ.addFirst(5);
        DQ.addFirst(11);
        DQ.addLast(7);
        DQ.addLast(20);
        DQ.addFirst(39);
        DQ.addLast(6);
        DQ.addFirst(2);
        DQ.addLast(43);
        System.out.println("First: " + DQ.first());
        System.out.println("Last: " + DQ.last());
        
        while (!DQ.isEmpty()) {
            System.out.print(DQ.removeFirst() + " ");
        }
        System.out.println("");
    }
}
