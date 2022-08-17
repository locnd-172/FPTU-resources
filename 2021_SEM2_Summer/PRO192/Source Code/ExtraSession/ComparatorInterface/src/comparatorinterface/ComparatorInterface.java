package comparatorinterface;

import java.util.ArrayList;
import java.util.Collections;

public class ComparatorInterface {

    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("ID004", "Michel", 400));
        list.add(new Employee("ID001", "Helen", 200));
        list.add(new Employee("ID003", "Hemming", 400));
        list.add(new Employee("ID002", "Hemming", 400));
        Collections.sort(list);
        System.out.println(list);
        System.out.println("ID ascending, salary descending");
        Collections.sort(list, Employee.compareObj);
        System.out.println(list);
    }
    
}
