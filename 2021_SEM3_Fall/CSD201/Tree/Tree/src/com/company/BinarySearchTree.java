package com.company;

public class BinarySearchTree<T extends  Comparable<? super T>> {
    private Node<T> root = null;
    public BinarySearchTree() {
        root = null;
    }
    private void clear() {
        root = null;
    }
    private boolean isEmpty() {
        return root == null;
    }
    public int size() {
        return size(root);
    }
    private int size(Node<T> root) {
        if (root == null)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }
    private Node<T> createNewNode(T x) {
        Node<T> newNode = new Node<>(x);
        return newNode;
    }
    public void insertNode(T x) {
        root = insertNode(root, x);
    }

    private Node<T> insertNode(Node<T> root, T x) {
        if (root == null) {
            return createNewNode(x);
        }
        if (root.key.compareTo(x) > 0)
            root.left = insertNode(root.left, x);
        else if (root.key.compareTo(x) < 0)
            root.right = insertNode(root.right, x);
        return root;
    }
    //[30, 20, 40, 15, 25, 37, 45]
    public void createTree(T[] a, int n) {
        root = null;
        for (int i = 0; i < n; i++) {
            root = insertNode(root, a[i]);
        }
    }
    public Node<T> searchNode(T x) {
        return searchNode(root, x);
    }
    private Node<T> searchNode(Node<T> root, T x) {
        if (root == null || root.key.equals(x)) {
            return root;
        }
        if (root.key.compareTo(x) < 0) {
            return searchNode(root.right, x);
        }
        return searchNode(root.left, x);
    }
    public void deleteNode(T x) {
        root = deleteNode(root, x);
    }

    private Node<T> deleteNode(Node<T> root, T x) {
        if (root == null) {
            return root;
        }
         if (root.key.compareTo(x) > 0) {
             root.left = deleteNode(root.left, x);
         }
         else if (root.key.compareTo(x) < 0) {
             root.right = deleteNode(root.right, x);
         }
         else {
            if (root.left == null) {
                Node<T> temp = root.right;
                root = null;
                return temp;
            }
            else if (root.right == null) {
                Node<T> temp = root.left;
                root = null;
                return temp;
            }
            Node<T> temp = minValueNode(root.right);
            root.key = temp.key;
            root.right = deleteNode(root.right, temp.key);
         }
         return root;
    }
    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    public void deleteTree() {
        deleteTree(root);
    }
    private void deleteTree(Node<T> root) {
        if (root == null)
            return;
        deleteTree(root.left);
        deleteTree(root.right);
        root = null;
    }
    public void inTraversalTree() {
        inTraversalTree(root);
    }
    private void inTraversalTree(Node<T> root) {
        if (root != null) {
            inTraversalTree(root.left);
            System.out.println(root.key + " ");
            inTraversalTree(root.right);
        }
    }
    public void preTraversalTree() {
        preTraversalTree(root);
    }
    private void preTraversalTree(Node<T> root) {
        if (root != null) {
            System.out.println(root.key + " ");
            preTraversalTree(root.left);
            preTraversalTree(root.right);
        }
    }
    public void postTraversalTree() {
        postTraversalTree(root);
    }
    private void postTraversalTree(Node<T> root) {
        if (root != null) {
            postTraversalTree(root.left);
            postTraversalTree(root.right);
            System.out.println(root.key + " ");
        }
    }
}
