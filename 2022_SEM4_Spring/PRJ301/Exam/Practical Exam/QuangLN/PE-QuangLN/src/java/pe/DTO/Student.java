/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.DTO;

/**
 *
 * @author Admin
 */
public class Student {
    private String rollNo;
    private String major;
    private String fullName;
    private String city;
    private int gender;

    public Student() {
    }

    public Student(String rollNo, String major, String fullName, String city, int gender) {
        this.rollNo = rollNo;
        this.major = major;
        this.fullName = fullName;
        this.city = city;
        this.gender = gender;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" + "rollNo=" + rollNo + ", major=" + major + ", fullName=" + fullName + ", city=" + city + ", gender=" + gender + '}';
    }
    
    
    
}
