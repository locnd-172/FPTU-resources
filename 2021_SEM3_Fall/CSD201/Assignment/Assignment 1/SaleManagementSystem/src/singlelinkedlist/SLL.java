package singlelinkedlist;

import data.Product;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */

public class SLL<T> {

    Node<T> head, tail;

    public SLL() {
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
            head = newNode;
        }
    }

    public void addLast(T data) {
        Node<T> newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
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

    public int size() {
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
            head = head.next;
        }
    }

    public void removeLast() {
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

    public void removeAll() {
        Node<T> newNode = head;
        while (head != null) {
            newNode = head.next;
            head = null;
            head = newNode;
        }
    }

    public T get(int pos) throws Exception {
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

    public void printAll() {
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

    public void swapNodes(T x, T y) {
        Node<T> prevX = null, curX = head;
        while (curX != null && curX.data != x) {
            prevX = curX;
            curX = curX.next;
        }
 
        Node<T> prevY = null, curY = head;
        while (curY != null && curY.data != y) {
            prevY = curY;
            curY = curY.next;
        }
 
        if (curX == null || curY == null) return;
        
        if (prevX != null) prevX.next = curY;
        else head = curY;
        if (prevY != null) prevY.next = curX;
        else head = curX;
 
        Node<T> tmp = curX.next;
        curX.next = curY.next;
        curY.next = tmp;
    }
    
    public Node<T> getNode(int pos) {
        int cnt = 0;
        Node<T> cur = head;
        while (cur != null) {
            if (cnt >= pos) break;
            cur = cur.next;
            ++cnt;
        }
        return cur;
    }
    
    public void swap(int x, int y) throws Exception {
        if(x >= size() || y >= size()) return;
        
        Node<T> node1 = getNode(x);
        Node<T> node2 = getNode(y);
         
        T tmp = (T) node1.data;
        node1.data = node2.data;
        node2.data = tmp;
    }
}
