package data;

import java.util.Scanner;
import util.Input;
import static util.Input.getADouble;
import static util.Input.getAnInteger;

public class Shelf {

    private Book arr[] = new Book[1000];
    private Scanner sc = new Scanner(System.in);
    private int count = 0;

    public void inputABook() {
        System.out.println("______________________");
        System.out.println("Input book list ");
        System.out.println("No." + (count + 1));

        String name, author, publisher;
        double price;
        int yop, nPages;

        System.out.print("Name: ");
        name = sc.nextLine();

        price = getADouble("Price: ", "Input a valid double please!");

        System.out.print("Author: ");
        author = sc.nextLine();

        System.out.print("Publisher: ");
        publisher = sc.nextLine();

        yop = getAnInteger("Year of publication: ", "Input a year!");
        nPages = getAnInteger("Number of pages: ", "Input a valid integer!");

        arr[count] = new Book(name, price, author, publisher, yop, nPages);
        ++count;
    }

    public void printBookList() {
        if (count == 0) {
            System.out.println("There is no book in the list!");
            return;
        }
        System.out.println("The book list");
        for (int i = 0; i < count; i++) {
            System.out.print("#" + (i + 1));
            arr[i].showInfo();
        }
    }

    public void sortBookList() {
        if (count == 0) {
            System.out.println("There is no book in the list!");
            return;
        }
        System.out.printf("1. sort ascending\n2: sort descending\nEnter: ");
        int type = sc.nextInt();

        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                double diff = arr[i].getPrice() - arr[j].getPrice();
                if (diff == 0) {
                    continue;
                }
                if (type == 1) {
                    if (diff > 0) {
                        Book tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                } else {
                    if (diff < 0) {
                        Book tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
        System.out.println("The book list after sort " + ((type == 1) ? "ascending" : "descending") + " by price");
        for (int i = 0; i < count; i++) {
            System.out.print("#" + (i + 1));
            arr[i].showInfo();
        }
    }
}
