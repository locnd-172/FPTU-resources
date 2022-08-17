package fileemployee;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.Collections;
import java.util.Vector;

        
/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class EmpList extends Vector<Employee> {
    Scanner sc = new Scanner(System.in);
    public EmpList() {
        super();
    }
    
    public void addFromFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String code = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                int salary = Integer.parseInt(stk.nextToken());
                
                Employee emp = new Employee(code, name, salary);
                this.add(emp);
            }
            bf.close(); fr.close();
            
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fName) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw);  // println()
            for (Employee x : this) {
                pw.println(x.getCode() + "," + x.getName() + "," + x.getSalary());
            }
            pw.close(); fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private int find(String aCode) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCode().equals(aCode)) return i;
        }
        return -1;
    }
    
    public void addNewEmp() {
        String newCode, newName;
        int salary;
        int pos;
        boolean valid = true;
        System.out.println("Enter new Employee details: ");
        do {
            System.out.print("code: ");
            newCode = sc.nextLine().toUpperCase();
            pos = find(newCode);
            valid = newCode.matches("E\\d{3}");
            if (pos >= 0) System.out.println("Code is duplicated");
            if (!valid) System.out.println("Code: E & 3 digits");
        } while (pos >= 0 || !valid);
        System.out.println("name: ");
        newName = sc.nextLine().toUpperCase();
        System.out.println("salary: ");
        salary = Integer.parseInt(sc.nextLine());
        this.add(new Employee(newCode, newName, salary));
        System.out.println("New employee has been added.");
    }
    
    public void removeEmp() {
        String code;
        System.out.println("Enter the code of the removed employee: ");
        code = sc.nextLine().toUpperCase();
        int pos = find(code);
        if (pos < 0) 
            System.out.println("The code does not exist");
        else {
            this.remove(pos);
            System.out.println(code + " has been removed");
        }
    }
    
   public void promote() {
       String code;
       System.out.println("Enter the code of promoted employee: ");
       code = sc.nextLine().toUpperCase();
       int pos = find(code);
       if (pos < 0) System.out.println("Code does not exist");
       else {
           int oldSalary = this.get(pos).getSalary();
           int newSalary;
           do {
               System.out.println("Old salary: " + oldSalary + ", new salary: ");
               newSalary = Integer.parseInt(sc.nextLine());
           } while (newSalary < oldSalary);
           this.get(pos).setSalary(newSalary);
           System.out.println(code + " has been updated.");
       }
   }
   
   public void printList() {
       if (this.size() == 0) {
           System.out.println("Empty list");
           return;
       }
       Collections.sort(this);
       System.out.println("\nEMPLOYEE LIST");
       System.out.println("______________________________________");
       for (Employee x : this) {
           x.showInfo();
           
       }
   }
}
