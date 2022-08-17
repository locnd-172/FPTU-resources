package bena;

import data.Circle;
import data.Rectangle;
import data.Square;
import java.util.Scanner;

public class BeNa {

    public static void main(String[] args) {
        Rectangle[] rec;
        rec = new Rectangle[3];

        Square[] sqr;
        sqr = new Square[3];

        Circle[] cir;
        cir = new Circle[3];

        inputProfile(rec, sqr, cir);
        showInformation(rec, sqr, cir);
    }

    public static void inputProfile(Rectangle[] rec, Square[] sqr, Circle[] cir) {
        inputRectangle(rec);
        inputSquare(sqr);
        inputCircle(cir);

    }

    public static void inputRectangle(Rectangle[] rec) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Rectangle #" + (i + 1));
            rec[i] = new Rectangle();

            System.out.print("Color: ");
            rec[i].setColor(sc.nextLine().toLowerCase());

            while (true) {
                try {
                    System.out.print("Width: ");
                    rec[i].setWidth(Double.parseDouble(sc.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Not a number!");
                }
            }

            while (true) {
                try {
                    System.out.print("Height: ");
                    rec[i].setHeight(Double.parseDouble(sc.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Not a number!");
                }
            }

            System.out.print("Owner: ");
            rec[i].setOwner(sc.nextLine().toUpperCase());

        }
        System.out.println("________________________");
    }

    public static void inputSquare(Square[] sqr) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Square #" + (i + 1));
            sqr[i] = new Square();

            System.out.print("Color: ");
            sqr[i].setColor(sc.nextLine().toLowerCase());

            while (true) {
                try {
                    System.out.print("Edge length: ");
                    sqr[i].setEdgeLength(Double.parseDouble(sc.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Not a number!");
                }
            }

            System.out.print("Owner: ");
            sqr[i].setOwner(sc.nextLine().toUpperCase());
        }
        System.out.println("________________________");
    }

    public static void inputCircle(Circle[] cir) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Circle #" + (i + 1));
            cir[i] = new Circle();

            System.out.print("Color: ");
            cir[i].setColor(sc.nextLine().toLowerCase());

            while (true) {
                try {
                    System.out.print("Radius: ");
                    cir[i].setRadius(Double.parseDouble(sc.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Not a number!");
                }
            }

            System.out.print("Owner: ");
            cir[i].setOwner(sc.nextLine().toUpperCase());
        }
        System.out.println("________________________");
    }

    public static void showInformation(Rectangle[] rec, Square[] sqr, Circle[] cir) {
        for (int i = 0; i < 3; i++) {
            rec[i].showInfo();
        }

        for (int i = 0; i < 3; i++) {
            sqr[i].showInfo();
        }

        for (int i = 0; i < 3; i++) {
            cir[i].showInfo();
        }
    }
}
