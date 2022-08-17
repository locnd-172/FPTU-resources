package com.company;

public class Node <T extends  Comparable<? super T>> {
     T key;
     Node<T> left, right;
    public  Node(T item) {
        key = item;
        left = right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
