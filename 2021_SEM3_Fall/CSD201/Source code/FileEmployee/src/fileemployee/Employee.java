package fileemployee;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Employee implements Comparable {
    private String code;
    private String name;
    private int salary;

    public Employee(String code, String name, int salary) {
        this.code = code;
        this.name = name;
        this.salary = salary;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "code=" + code + ", name=" + name + ", salary=" + salary + '}';
    }
    
    
    public void showInfo() {
        System.out.printf("%-6s\t%-20s\t%-4d\n", 
                            code, name, salary);
    }
    
    @Override
    public int compareTo(Object emp) {
        return this.getCode().compareTo(((Employee)emp).getCode());
    }
}
