package doublylinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class DLL {

    Node head, tail;

    public DLL() {

    }

    public DLL(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            newNode.prev = null;
            if (head != null) 
                head.prev = newNode;
            head = newNode;
        }
    }
    
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = null;
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public void addMany(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            addLast(a[i]);
        }
    }
    
    public void addPos(int pos, int data) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos > size() + 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addFirst(data);
        } else if (pos == size() + 1) {
            addLast(data);
        } else {
            int cnt = 0;
            Node curNode = head;
            while (true) {
                ++cnt;
                if (cnt >= pos) {
                    break;
                }
                curNode = curNode.next;
                if (curNode == null) break;
            }
            Node newNode = new Node(data);
            
            newNode.next = curNode;
            newNode.prev = curNode.prev;
            curNode.prev.next = newNode;
            curNode.prev = newNode;
            
        }
    }
    
    int size() {
        int cnt = 0;
        Node curNode = head;
        while (curNode != null) {
            ++cnt;
            curNode = curNode.next;
        }
        return cnt;
    }
    
    public void removeFirst() {
        if (isEmpty()) {
            return;
        } else {
            if (head.next != null) {
                head = head.next;
                head.prev = null;
            } else {
                head = null;
            }
        }
    }

    
    public void removeLast() {
        if (isEmpty())
            return;
        else {
            if (tail.prev != null) {
                tail = tail.prev;
                tail.next = null;
            } else {
                head = tail = null;
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
        } else {
            int cnt = 0;
            Node curNode = head;
            while (curNode != null) {
                ++cnt;
                if (cnt >= pos) {
                    break;
                }
                curNode = curNode.next;
            }
            if (curNode.next == null) {
                removeLast();
                return;
            }
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
            curNode = null;
        }
    }
    
    public void removeAll() {
        Node curNode = head;
        while (head != null) {
            curNode = head.next;
            head = null;
            head = curNode;
        }
    }
    
    int get(int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return 0;
        }
        if (pos > size()) {
            System.out.println("Invalid position.");
            return 0;
        }
        int cnt = 0;
        Node curNode = head;
        while (curNode != null) {
            ++cnt;
            if (cnt >= pos) {
                break;
            }
            curNode = curNode.next;
        }
        return curNode.data;
    }
    
    void indexOf(int val) {
        int cnt = 0, check = 0;
        Node curNode = head;
        while (curNode != null) {
            ++cnt;
            if (curNode.data == val) {
                check = 1;
                System.out.print(cnt + " ");
            }
            curNode = curNode.next;
        }
        if (check == 0)
            System.out.println(-1);
    }
    
    public void printAll() { 
        Node curNode = head;
        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
    } 
}
