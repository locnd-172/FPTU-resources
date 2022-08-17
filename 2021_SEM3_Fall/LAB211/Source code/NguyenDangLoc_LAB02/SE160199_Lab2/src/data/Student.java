package data;

import java.io.Serializable;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Student implements Serializable {
    private String studentId;
    private String studentName;

    public Student(String studentID, String studentName) {
        this.studentId = studentID;
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s", studentId, studentName);
    }
    
    public void showInfo() {
        System.out.println(this.toString());
    }
    
}
