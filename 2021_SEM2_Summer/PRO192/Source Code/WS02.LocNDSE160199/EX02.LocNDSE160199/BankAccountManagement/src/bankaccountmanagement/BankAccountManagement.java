package bankaccountmanagement;

import data.Account;
import java.util.Scanner;

public class BankAccountManagement {
    public static void main(String[] args) {
        // I assume that minimum balance is 50,000VND and the maximum available is 1,000,000VND
        
        Account[] man;
        man = new Account[5];
        
        man[0] = new Account(275471956614L, "Nguyen Van An", "285719836", "0357 543 650", getRandNumber(50, 1000)*1000);
        man[1] = new Account(235647895642L, "Hoang Cong Minh", "285733456", "0356 211 418", getRandNumber(50, 1000)*1000);
        man[2] = new Account(356426415785L, "Le Van Binh", "285777468", "0575 634 223", getRandNumber(50, 1000)*1000);
        man[3] = new Account(345641247896L, "Nguyen Thi Thuy", "285302635", "0553 456 745", getRandNumber(50, 1000)*1000);
        man[4] = new Account(456789423177L, "Duong Van Duc", "285666745", "0564 569 875", getRandNumber(50, 1000)*1000);
        
        //Print Account List
        for (int i = 0; i < 5; ++i) {
            System.out.println("No." + (i+1));
            man[i].printAccountInfo();
            System.out.println("_________________");
        }
        
        //Cash Withdraw
        System.out.println("CASH WITHDRAW");
        withdrawCash(man);
        
        //Account Inquiries
        System.out.println("_________________");
        System.out.println("ACCOUNT INQUIRY");
        findIndexAccount(man);
    }
    
    public static int getAnInteger() {
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        return n;
    }
    
    public static long getALongNumber() {
        long n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextLong();
        return n;
    }
    
    private static int getRandNumber(int min, int max) {
        if (min >= max) return 0;
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    
    public static int findIndex(Account[] man) {
        System.out.print("Enter account number: ");
        long accNumber = getALongNumber();
        int index = -1;
        for (int i = 0; i < 5; ++i) {
            if (accNumber == man[i].getAccountNumber()) {
                index = i; break;
            }
        }
        return index;
    }
    
    public static void findIndexAccount(Account[] man) {       
        int index = findIndex(man);       
        if (index == -1) {
            System.out.println("Account is not found!");
            return;
        }
        man[index].printAccountInfo();
        
    }

    public static void withdrawCash(Account[] man) {
        int index = findIndex(man);       
        if (index == -1) {
            System.out.println("Account is not found!");
            return;
        }
        
        int amount, curBalance = man[index].getBalance();
        System.out.println("Name: " + man[index].getName());
        System.out.println("Current balance: " + curBalance + " VND");
        System.out.println("");
        
        System.out.println("Enter amount of cash you want to withdraw");
        
        int flag = 0;
        while (true) {
            if (flag == 1) break;
            System.out.print("Amount: ");
            amount = getAnInteger();
            flag = 1;
            
            if (amount < 50000) {
                System.out.println("Minimum amount to transaction is 50,000VND!");
                flag = 0;
                continue;
            }
            if (amount % 10000 != 0) {
                System.out.println("The amount must be a multiple of 10,000!");
                flag = 0;
                continue;
            }
            
            int newBalance = curBalance - amount;
            if (newBalance < 50000) {
                System.out.println("Invalid amount! Your balance after transaction must be at least 50,000VND");
                flag = 0;
                continue;
            }
            
            man[index].setBalance(newBalance);
            System.out.println("Successfully transaction!");
            System.out.println("New balance: " + newBalance + " VND");
        }
       
    }
 }
