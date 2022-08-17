package doublylinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 * @param <T>
 */
public class DLL<T extends Comparable<T>> {

    Node<T> head, tail;

    public DLL() {
    }

    public DLL(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public void addFirst(T data) {
        Node<T> newNode = new Node(data);
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
    
    public void addLast(T data) {
        Node<T> newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = null;
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public void addMany(T[] a) {
        for (int i = 0; i < a.length; ++i) {
            addLast(a[i]);
        }
    }
    
    public void addPos(int pos, T data) {
        if (pos < 0 || pos > size() ) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 0) {
            addFirst(data);
        } else if (pos == size()) {
            addLast(data);
        } else {
            int cnt = 0;
            Node<T> curNode = head;
            while (true) {
                ++cnt;
                if (cnt >= pos) {
                    break;
                }
                curNode = curNode.next;
                if (curNode == null) break;
            }
            Node<T> newNode = new Node(data);
            
            newNode.next = curNode;
            newNode.prev = curNode.prev;
            curNode.prev.next = newNode;
            curNode.prev = newNode;
            
        }
    }
    
    int size() {
        int cnt = 0;
        Node<T> curNode = head;
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
            Node<T> curNode = head;
            while (curNode != null) {
                ++cnt;
                if (cnt >= pos) {
                    break;
                }
                curNode = curNode.next;
            }
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
            curNode = null;
        }
    }
    
    public void removeAll() {
        Node<T> curNode = head;
        while (head != null) {
            curNode = head.next;
            head = null;
            head = curNode;
        }
    }
    
    T get(int pos) throws Exception {
        if (isEmpty()) {
            throw new NullPointerException("Empty list");
        }
        if (pos < 0 || pos > size() - 1) {
            throw new Exception("Invalid position");
        }
        int cnt = 0;
        Node<T> curNode = head;
        while (curNode != null) {
            if (cnt >= pos) break;
            ++cnt;
            curNode = curNode.next;
        }
        return curNode.data;
    }
    
    void indexOf(T val) {
        int cnt = 0, check = 0;
        Node curNode = head;
        while (curNode != null) {
            if (curNode.data.equals(val)) {
                check = 1;
                System.out.print(cnt + " ");
            }
            ++cnt;
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
