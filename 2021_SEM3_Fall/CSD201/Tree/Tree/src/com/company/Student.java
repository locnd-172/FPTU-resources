package com.company;

import java.util.Comparator;

public class Student implements Comparable<Student > {
    private String studentId;
    private String studentName;
    private double gpa;
    Student left, right;
    public Student() {
        left = null;
        right = null;
    }
    public Student(String studentId, String studentName, double gpa) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gpa = gpa;
    }

    public String getStudentId() {
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public int compareTo(Student st) {
        return this.getStudentId().compareTo(st.getStudentId());
    }
}
