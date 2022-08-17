package usingstack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 * @param <T>
 */
public class SLL<T extends Comparable<T>> {

    Node<T> head, tail;

    public SLL() {
    }

    boolean isEmpty() {
        return head == null;
    }

    void addFirst(T data) {
        Node<T> newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    void addLast(T data) {
        Node<T> newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    void addMany(T[] a) {
        for (int i = 0; i < a.length; ++i) {
            addLast(a[i]);
        }
    }

    void addPos(int pos, T data) {
        if (pos < 0 || pos > size()) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 0) {
            addFirst(data);
        } else if (pos == size()) {
            addLast(data);
        } else {
            int cnt = 0;
            Node curNode = head, pre = null;
            while (curNode != null) {
                if (cnt >= pos) break;
                ++cnt;
                pre = curNode;
                curNode = curNode.next;
            }
            Node<T> newNode = new Node(data);
            pre.next = newNode;
            newNode.next = curNode;
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

    void removeFirst() {
        if (isEmpty()) {
            return;
        } else {
            head = head.next;
        }
    }

    void removeLast() {
        if (isEmpty()) {
            return;
        }
        Node<T> curNode = head, pre = null;
        while (curNode != null) {
            if (curNode.next == null) {
                if (curNode == head) {
                    head = head.next = null;
                } else {
                    tail = pre;
                    tail.next = null;
                }
                break;
            } else {
                pre = curNode;
                curNode = curNode.next;
            }
        }
    } 

    void removePos(int pos) {
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
            Node<T> curNode = head, pre = null;
            while (curNode != null) {
                if (cnt >= pos) break;
                ++cnt;
                pre = curNode;
                curNode = curNode.next;
            }
            pre.next = curNode.next;
            curNode = curNode.next = null;

        }
    }

    void removeAll() {
        Node<T> newNode = head;
        while (head != null) {
            newNode = head.next;
            head = null;
            head = newNode;
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
        return (T)curNode.data;
    }

    int indexOf(T val) { // first index
        int cnt = 0;
        Node<T> curNode = head;
        while (curNode != null) {
            if (curNode.data.compareTo(val) == 0) {
                return cnt;
            }
            ++cnt;
            curNode = curNode.next;
        }
        return -1;
    }

    void printAll() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        Node<T> curNode = head;
        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
    }
}
