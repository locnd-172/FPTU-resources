package com.company;

import java.util.Scanner;

public class StudentTree  {
    BinarySearchTree<Student> studentTree = new BinarySearchTree<>();
    private Scanner sc = new Scanner(System.in);
    Student root;

    public StudentTree() {
        root = null;
    }
    public int size() {
        return size(root);
    }
    private int size(Student root) {
        if (root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
    public Student search(String id) {
        return search(root, id);
    }
    private Student search(Student root, String id) {
        if (root == null || root.getStudentId().equals(id)) {
            return root;
        }
        if (root.getStudentId().compareTo(id) < 0) {
            return search(root.right, id);
        }
        return search(root.left, id);
    }
    public void insert() {
        String id;
        String name;
        double gpa;
        String option;
        Student p = null;
        do {
            do {
                System.out.print("Enter student id: ");
                id = sc.nextLine().trim().toUpperCase();
                p = this.search(id);
                if (p!=null) System.out.println("This id is duplicated!");
            }
            while(p!=null);
            System.out.print("Enter student name: ");
            name = sc.nextLine();
            do {
                System.out.print("Enter student GPA: ");
                gpa = Double.parseDouble(sc.nextLine());
                if (gpa < 0 || gpa > 4) System.out.println("Gpa must be >= 0 and <= 4");
            } while (gpa < 0 || gpa > 4);
            root = insert(root, id, name, gpa);
            System.out.println("Student added!");
            do {
                System.out.println("Do you want to add more student? (y/n)");
                option = sc.nextLine();
                if (!(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n"))) {
                    System.out.println("Please enter (y) or (n)!");
                }
            }while (!(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")));

        } while (option.equalsIgnoreCase("y"));
    }
    private Student insert (Student root, String id, String name, double gpa) {
        if (root == null) {
            return new Student(id, name, gpa);
        }
        if (root.getStudentId().compareTo(id) > 0)
            root.left = insert(root.left, id, name, gpa);
        else if (root.getStudentId().compareTo(id) < 0)
            root.right = insert(root.right, id, name, gpa);
        return root;
    }

    public void delete(String id) {
        root = delete(root, id);
    }
    private Student delete(Student root, String id) {
        if (root == null) {
            return null;
        }
        if (root.getStudentId().compareTo(id) > 0) {
            root.left = delete(root.left, id);
        }
        else if (root.getStudentId().compareTo(id) < 0) {
            root.right = delete(root.right, id);
        }
        else {
            if (root.left == null) {
                Student temp = root.right;
                root = null;
                return temp;
            }
            else if (root.right == null) {
                Student temp = root.left;
                root = null;
                return temp;
            }
            Student temp = minValueNode(root.right);
            root.setStudentId(temp.getStudentId());
            root.setStudentName(temp.getStudentName());
            root.setGpa(temp.getGpa());
            root.right = delete(root.right, temp.getStudentId());
        }
        return root;
    }
    private Student minValueNode(Student node) {
        Student current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    public void inTraversal() {
        inTraversal(root);
    }
    private void inTraversal(Student root) {
        if (root != null) {
            inTraversal(root.left);
            System.out.println(root);
            inTraversal(root.right);
        }
    }
    public void preTraversalTree() {
        preTraversalTree(root);
    }
    private void preTraversalTree(Student root) {
        if (root != null) {
            System.out.println(root + " ");
            preTraversalTree(root.left);
            preTraversalTree(root.right);
        }
    }
    public void postTraversalTree() {
        postTraversalTree(root);
    }
    private void postTraversalTree(Student root) {
        if (root != null) {
            postTraversalTree(root.left);
            postTraversalTree(root.right);
            System.out.println(root + " ");
        }
    }
    public void updateGpa() {
        String option;
        double gpa;
        System.out.print("Enter student id you want to update GPA: ");
        String id = sc.nextLine().trim().toUpperCase();
        if (search(id) == null) System.out.println("Student does not exist!");
        else {
            do {
                System.out.print("Enter student GPA: ");
                gpa = Double.parseDouble(sc.nextLine());
                if (gpa < 0 || gpa > 4) System.out.println("Gpa must be >= 0 and <= 4");
            } while (gpa < 0 || gpa > 4);
            do {
                System.out.println("Do you want to update? (y/n)");
                option = sc.nextLine();
            } while (!(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")));
            if (option.equalsIgnoreCase("y")) {
                search(id).setGpa(gpa);
                System.out.println("Updated!");
                return;
            }
            else {
                System.out.println("Failed!");
                return;
            }
        }
    }

}
