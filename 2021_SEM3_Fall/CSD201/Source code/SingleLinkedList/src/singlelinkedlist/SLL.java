package singlelinkedlist;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class SLL {

    Node head, tail;

    public SLL() {
    }

    boolean isEmpty() {
        return head == null;
    }

    void addFirst(int info) {
        Node newNode = new Node(info);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    void addLast(int info) {
        Node newNode = new Node(info);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    void addMany(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            addLast(a[i]);
        }
    }

    void addPos(int pos, int info) {
        if (pos < 1 || pos > size() + 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 0) {
            addFirst(info);
        } else if (pos == size()) {
            addLast(info);
        } else {
            int cnt = 0;
            Node curNode = head, pre = null;
            while (curNode != null) {
                if (cnt >= pos) break;
                ++cnt;
                pre = curNode;
                curNode = curNode.next;
            }
            Node newNode = new Node(info);
            pre.next = newNode;
            newNode.next = curNode;
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
        Node curNode = head, pre = null;
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
        if (pos < 1 || pos > size()) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            removeFirst();
        } else if (pos == size() - 1) {
            removeLast();
        } else {
            int cnt = 0;
            Node curNode = head, pre = null;
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
        Node newNode = head;
        while (head != null) {
            newNode = head.next;
            head = null;
            head = newNode;
        }
    }

    int get(int pos) throws Exception {
        if (isEmpty()) {
            throw new NullPointerException("Empty list.");
        }
        if (pos < 1 || pos > size() ) {
            throw new Exception("Invalid position");
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
        return curNode.info;
    }

    int indexOf(int val) {
        int cnt = 0;
        Node curNode = head;
        while (curNode != null) {
            if (curNode.info == val) {
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
        Node curNode = head;
        while (curNode != null) {
            System.out.print(curNode.info + " ");
            curNode = curNode.next;
        }
    }
}
