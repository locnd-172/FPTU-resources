/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Student{
    int id;
    String name;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
 
    public Student getStudent() {
        return this;
    }
}