package pe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student ORDER BY Major";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String rollNo = rs.getString("RollNo");
                    String major = rs.getString("Major");
                    String fullName = rs.getString("FullName");
                    String city = rs.getString("City");
                    int male = rs.getInt("Male");
                    Student student = new Student(rollNo, major, fullName, city, male);
                    list.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ArrayList<Student> getStudentByMajor(String major) {
        Connection cn = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student WHERE Major=? ORDER BY Major";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, major);
                ResultSet rs = pst.executeQuery();

                while (rs.next() && rs != null) {
                    String rollNo = rs.getString("RollNo");
                    String fullName = rs.getString("FullName");
                    String city = rs.getString("City");
                    int male = rs.getInt("Male");
                    Student student = new Student(rollNo, major, fullName, city, male);
                    list.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    public static Student getStudentByID(String rollNo) {
        Connection cn = null;
        Student student = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT RollNo, Major, FullName, City, Male FROM Student WHERE RollNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rollNo);
                ResultSet rs = pst.executeQuery();

                while (rs.next() && rs != null) {
                    String fullName = rs.getString("FullName");
                    String major = rs.getString("Major");
                    String city = rs.getString("City");
                    int male = rs.getInt("Male");
                    student = new Student(rollNo, major, fullName, city, male);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    public static boolean insertStudent(String rollNo, String major, String fullName, String city, int male) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO Student(RollNo, Major, FullName, City, Male) VALUES(?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rollNo);
                pst.setString(2, major);
                pst.setString(3, fullName);
                pst.setString(4, city);
                pst.setInt(5, male);
                res = pst.executeUpdate();

                return (res == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return (res == 1);
        }
    }

    public static boolean updateStudent(String rollNo, String newMajor, String newFullName, String newCity, int newMale) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Student SET Major=?, FullName=?, City=?, Male=? WHERE RollNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newMajor);
                pst.setString(2, newFullName);
                pst.setString(3, newCity);
                pst.setInt(4, newMale);
                pst.setString(5, rollNo);
                res = pst.executeUpdate();

                return (res == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return (res == 1);
        }
    }
    
    public static boolean deleteStudent(String rollNo) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM Student WHERE RollNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rollNo);
                res = pst.executeUpdate();

                return (res == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return (res == 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(updateStudent("SE16018", "Engineering", "Nguyen Van An", "HCM", 0));
    }
}
