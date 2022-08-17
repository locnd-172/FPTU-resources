package ws1;

import java.util.Scanner;

/**
    @by Dang Loc Nguyen
 */

public class WS1 {
    
    public static void main(String[] args) {
        //ex01
        checkPrimeNumber();
        System.out.println();
        
        //ex02
        printPrimeNumberList();
        System.out.println();
        
        //ex03
        print1000FirstPrimeNumbers();
        System.out.println();
        
        //ex04
        solveEquation();
        System.out.println();
    }
    
    public static int getAPositiveInteger() {
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
               
        return n;
    }
    
    public static void checkPrimeNumber() {
        int n;
        System.out.println("1. Check whether N is a prime number");
        System.out.print("Input N: ");
        n = getAPositiveInteger();
        
        if (isPrime(n) == true) 
            System.out.println(n + " is a prime number.");
        else 
            System.out.println(n + " is not a prime number.");
    }
    
    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) 
            if (n % i == 0)
                return false;
        
        return (n > 1);
    }
    
    public static void printPrimeNumberList() {
        System.out.println("2. Print prime numbers in range [1 ... 1000]");
        
        for (int i = 1; i < 1001; ++i) {
            if (isPrime(i) == true) 
                System.out.print(i + " ");
            if (i % 100 == 0) 
                System.out.println();
        }
    }
     
    public static void print1000FirstPrimeNumbers() {
        System.out.println("3. Print 1000 first prime numbers");
        
        int countPrime = 1, n = 2;
        while (true) {
            if (countPrime >= 1000) break;
            if (isPrime(n) == true) {
                System.out.printf("%4d ", n);
                ++countPrime;
             
                if (countPrime % 20 == 0) 
                    System.out.println();
            }
            ++n;
        }
    }
 
    public static double getADouble() {
        double n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextDouble();
               
        return n;  
    }
    
    public static void solveEquation() {
        System.out.println("4. Solve the linear equation: ax + b = 0");
        
        double a, b;
        System.out.print("a = "); 
        a = getADouble();
        System.out.print("b = ");
        b = getADouble();
        
        if (a == 0) {
            if (b == 0) 
                System.out.println("Infinite solution.");
            else 
                System.out.println("No solution.");
        }
        else {
            double result = (-b) / a;
            System.out.printf("x = %.4f", result);
        }          
    }
    
}
