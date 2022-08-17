package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Test với số
//    Integer[] a = {30, 20, 40, 15, 25, 37, 45};
//    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//    tree.createTree(a, a.length);
//    tree.inTraversalTree();
    Scanner sc = new Scanner(System.in);
        StudentTree st = new StudentTree();
        int choice;
        do {
            System.out.println("Student Management - Binary Tree");
            System.out.println("1. Add new student");
            System.out.println("2. Update student GPA by student id");
            System.out.println("3. Search student by student id");
            System.out.println("4. Delete student by student id");
            System.out.println("5. Print student list");
            System.out.println("6. Exit");
            choice = choice();
            switch (choice) {
                case 1 -> st.insert();
                case 2 -> {
                    if (st.size() == 0) {
                        System.out.println("Nothing to update");
                        break;
                    }
                    st.updateGpa();
                }
                case 3 -> {
                    if (st.size() == 0) {
                        System.out.println("Nothing to search");
                        break;
                    }
                    System.out.print("Student id: ");
                    String id1 = sc.nextLine();
                    System.out.println(st.search(id1));
                }
                case 4 -> {
                    if (st.size() == 0) {
                        System.out.println("Nothing to delete");
                        break;
                    }
                    System.out.print("Student id: ");
                    String id2 = sc.nextLine();
                    if (st.search(id2) == null) {
                        System.out.println("This student does not exist");
                    } else {
                        st.delete(id2);
                        System.out.println("Delete successfully");
                    }
                }
                case 5 -> {
                    if (st.size() == 0) {
                        System.out.println("Nothing to print");
                        break;
                    }
                    st.inTraversal();
                }
                case 6 -> System.out.println("Good bye");
            }
        }while(choice != 6);
    }
    public static final Scanner sc = new Scanner(System.in);
    public static int choice () {
        int choice;
        do {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 6 || choice < 0) {
                    System.out.println("Please enter from 1 to 6");
                }
        }while (choice < 0 || choice > 6);
        return choice;
    }
}
