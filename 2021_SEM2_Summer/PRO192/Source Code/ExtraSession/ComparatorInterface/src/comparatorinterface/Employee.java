package comparatorinterface;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

    private String ID, name;
    private int sal;

    public Employee(String ID, String name, int sal) {
        this.ID = ID;
        this.name = name;
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "[" + "ID=" + ID + ", name=" + name + ", sal=" + sal + "]";
    }
    
    @Override
    public int compareTo(Employee emp) {
        return ID.compareTo((emp).ID);
    }
//    public class Employee implements Comparable {
//    @Override
//    public int compareTo(Object emp) {
//        return ID.compareTo(((Employee)emp).ID);
//    }       

    public static Comparator<Employee> compareObj = (Employee emp1, Employee emp2) -> {
        //Employee emp1 = (Employee) e1;
        //Employee emp2 = (Employee) e2;
        int d = emp1.sal - emp2.sal;
        if (d > 0) return 1;
        if (d == 0) return emp1.ID.compareTo(emp2.ID);
        return -1;
    };
}
