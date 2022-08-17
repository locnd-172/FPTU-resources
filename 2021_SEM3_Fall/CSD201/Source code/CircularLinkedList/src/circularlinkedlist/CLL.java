package circularlinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class CLL {

    Node tail;

    public CLL() {
    }

    public CLL(Node tail) {
        this.tail = tail;
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
    }
    
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addMany(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            addLast(a[i]);
        }
    }

    public void addPos(int pos, int data) throws Exception {
        if (pos < 0 || pos > size()) {
            throw new Exception("Invalid position.");
        }
        if (pos == 0) {
            addFirst(data);
        } else if (pos == size()) {
            addLast(data);
        } else {
            int cnt = 0;
            Node curNode = tail.next, pre = null;
            do {
                if (cnt >= pos) break;
                ++cnt;
                pre = curNode;
                curNode = curNode.next;
            } while (curNode != tail.next);
            
            Node newNode = new Node(data);
            pre.next = newNode;
            newNode.next = curNode;
        }
    }

    public int size() {
        if (isEmpty()) return 0;
        
        int cnt = 0;
        Node curNode = tail.next;
        do {
            ++cnt;
            curNode = curNode.next;
        } while (curNode != tail.next);
        return cnt;
    }
  
    public void removeFirst() {
        if (isEmpty()) {
            return;
        } else if (size() == 1) {
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        } else if (size() == 1) {
            tail = tail.next = null;
        } else {
            Node curNode = tail.next;
            while (curNode.next != tail) {
                curNode = curNode.next;
            }
            curNode.next = tail.next;
            tail = curNode;
        }
    }

    public void removePos(int pos) {
        if (pos < 0 || pos > size() - 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 0) {
            removeFirst();
        } else if (pos == size() - 1) {
            removeLast();
        } else {
            int cnt = 0;
            Node curNode = tail.next, pre = null;
            do {
                if (cnt >= pos) break;
                ++cnt;
                pre = curNode;
                curNode = curNode.next;
            } while (curNode != tail.next);

            pre.next = curNode.next;
        }
    }

    public void removeAll() {
        tail = tail.next = null;
    }

    void rotate(int step) {
        if (step == 0) return;
        Node curNode = tail.next;
        for (int i = 1; i < step; i++) {
            curNode = curNode.next;
        }
        Node head = curNode.next;
        tail = curNode;
        tail.next = head;
        
    }
    
    public int get(int pos) {
         if (pos < 0 || pos > size() - 1) {
            System.out.println("Invalid position.");
            return -1;
        }
        int cnt = 0;
        Node curNode = tail.next;
        do {
            if (cnt >= pos) break;
            ++cnt;
            curNode = curNode.next;
        } while (curNode != tail.next);
        return curNode.data;
    }

    public void indexOf(int val) {
        int cnt = 0, check = 0;
        Node curNode = tail.next;
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        do {
            if (curNode.data == val) {
                check = 1;
                System.out.print(cnt + " ");
            }
            ++cnt;
            curNode = curNode.next;
        } while (curNode != tail.next);
        if (check == 0) System.out.println("Not found");
    }
    
    public void printAll() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        Node curNode = tail.next;
        do {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        } while (curNode != tail.next);
    }
}

/*
    public void removeLast() {
        if (isEmpty()) {
            return;
        } else if (size() == 1) {
            head = tail = null;
        } else {
            Node curNode = head, pre = head;
            while (true) {
                if (curNode.next == head) {
                    tail = pre;
                    tail.next = head;
                    break;
                } else {
                    pre = curNode;
                    curNode = curNode.next;
                }
            }
        }
    }

    public void removePos(int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos > size()) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            removeFirst();
        } else if (pos == size()) {
            removeLast();
            return;
        } else {
            int cnt = 0;
            Node curNode = head, pre = head, suf = head;
            while (curNode.next != head) {
                ++cnt;
                if (cnt >= pos) {
                    suf = curNode.next;
                    break;
                }
                pre = curNode;
                curNode = curNode.next;
            }

            pre.next = suf;
            curNode = null;

        }
    }
*/

