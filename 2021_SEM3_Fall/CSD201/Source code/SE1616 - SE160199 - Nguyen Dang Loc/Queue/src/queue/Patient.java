package queue;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Patient {
    int priority;
    String name;
    
    public Patient() {
        
    }
    
    public Patient(int pr) {
        this.priority = pr;
    }

    public Patient(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return priority + ", " + name;
    }   

}
