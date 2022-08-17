package hanoitower;

import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class HanoiTower {

    static void moveDisk(char from, char to, int disk) {
        System.out.println("Move " + disk + " from " + from + " to " + to);
    }

    static void rHanoiTower(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move 1 from " + A + " to " + B);
            return;
        }
        rHanoiTower(n - 1, A, C, B);
        System.out.println("Move " + n + " from " + A + " to " + B);
        rHanoiTower(n - 1, C, B, A);
    }

    public static void main(String[] args) {
        int N = 3;
        System.out.println("Move " + N + " disks from A to B using C as auxiliary disk\n");
        rHanoiTower(N, 'A', 'B', 'C');
        System.out.println("");
        Stack<Character> A = new Stack<>();
        Stack<Character> B = new Stack<>();
        Stack<Character> C = new Stack<>();
        Stack<Integer> count = new Stack<>();

        char a = 'A', b = 'B', c = 'C';

        count.add(N);
        A.add(a);
        B.add(b);
        C.add(c);

        while (!count.empty()) {

            int x = count.pop();
            char cura = A.pop();
            char curb = B.pop();
            char curc = C.pop();

            if (x == 1) {
                System.out.println(cura + " to " + curb);
                continue;
            }
            count.add(x - 1);
            A.add(curc);
            B.add(curb);
            C.add(cura);

            count.add(1);
            A.add(cura);
            B.add(curb);
            C.add(curc);

            count.add(x - 1);
            A.add(cura);
            B.add(curc);
            C.add(curb);
        }

    }

}
