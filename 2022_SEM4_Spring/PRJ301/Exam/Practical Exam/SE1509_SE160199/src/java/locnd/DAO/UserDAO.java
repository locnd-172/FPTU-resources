/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import locnd.DTO.User;
import locnd.Utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public static ArrayList<User> getUsers() {
        Connection cn = null;
        ArrayList<User> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT userID, fullname, roleID, [password] FROM [tblUsers]";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    String pwd = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    User user = new User(userID, fullname, roleID, pwd);
                    list.add(user);
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

    public static User getUserByID(String userID) {
        Connection cn = null;
        User user = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT userID, fullname, roleID, [password] FROM [tblUsers] WHERE userID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                ResultSet rs = pst.executeQuery();

                while (rs.next() && rs != null) {
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    String pwd = rs.getString("password");
                    user = new User(userID, fullname, pwd, roleID);
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
        return user;
    }

    public static User getUser(String userID, String password) {
        User user = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "SELECT userID, fullname, roleID, [password] FROM [tblUsers]"
                        + " WHERE userID=? and [password]=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                if (rs != null && rs.next()) {
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");

                    user = new User(userID, fullname, roleID, password);
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
        return user;
    }

    public static void main(String[] args) {
        System.out.println(getUser("admin", "12345"));
    }
}
