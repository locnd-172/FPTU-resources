/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.DTO.Student;
import pe.Utils.DBUtils;

/**
 *
 * @author Admin
 */
public class StudentDAO {

    public static ArrayList<Student> getStudents() {
        Connection cn = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String rollNo = rs.getString("RollNo");
                    String major = rs.getString("Major");
                    String fullname = rs.getString("FullName");
                    String city = rs.getString("City");
                    int gender = rs.getInt("Male");

                    Student student = new Student(rollNo, major, fullname, city, gender);
                    list.add(student);
                }
                cn.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ArrayList<Student> getStudentsByMajor(String major) {
        Connection cn = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student WHERE Major=? ORDER BY Major";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, major);
                ResultSet rs = pst.executeQuery();

                while (rs != null && rs.next()) {
                    String rollNo = rs.getString("RollNo");
                    String fullname = rs.getString("FullName");
                    String city = rs.getString("City");
                    int gender = rs.getInt("Male");

                    Student student = new Student(rollNo, major, fullname, city, gender);
                    list.add(student);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static boolean insertStudent(String rollNo, String major, String fullName, String city, int gender) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "INSERT INTO Student(RollNo, Major, FullName, City, Male) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rollNo);
                pst.setString(2, major);
                pst.setString(3, fullName);
                pst.setString(4, city);
                pst.setInt(5, gender);
                rs = pst.executeUpdate();

                return (rs == 1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return (rs == 1);
        }

    }

    public static Student getStudentByRollNo(String rollNo) {
        Connection cn = null;
        Student student = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student WHERE RollNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rollNo);
                ResultSet rs = pst.executeQuery();

                if (rs.next() && rs != null) {
                    String major = rs.getString("Major");
                    String fullname = rs.getString("FullName");
                    String city = rs.getString("City");
                    int gender = rs.getInt("Male");

                    student = new Student(rollNo, major, fullname, city, gender);
                }
                cn.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    public static void main(String[] args) {
//        boolean res = insertStudent("SE16018", "Business", "Dolphin", "Hanoi", 1);
        System.out.println(getStudentByRollNo("SE16011"));
    }
}
