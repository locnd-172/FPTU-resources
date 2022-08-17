package practicalexam;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Prob1 {

    public Prob1() {
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int n = 6;
        LinkedList<BigInteger> a = new LinkedList<>();
        BigInteger two = new BigInteger("2");

        for (int i = 1; i <= 2000; ++i) {
            if (a.size() == n) break;

            BigInteger p1 = two.pow(i - 1);
            BigInteger p2 = two.pow(i).subtract(BigInteger.valueOf(1));
            BigInteger prod = p1.multiply(p2);

            if (p2.isProbablePrime(1) == true)  a.add(prod);
        }

        for (int i = a.size() - 1; i > 0; --i) 
            System.out.print(a.get(i) + "->");

        System.out.println(a.get(0));
        
    }

}
