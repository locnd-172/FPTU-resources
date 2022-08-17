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
import pe.DTO.User;
import pe.Utils.DBUtils;

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
                String sql = "SELECT userID, fullname, [password], roleID, [status] FROM [User]";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("fullname");
                    String pwd = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    int status = rs.getInt("status");
                    User user = new User(userID, username, pwd, roleID, status);
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
                String sql = "SELECT userID, fullname, [password], roleID, [status] FROM [User] WHERE userID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                ResultSet rs = pst.executeQuery();

                while (rs.next() && rs != null) {
                    String username = rs.getString("fullname");
                    String pwd = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    int status = rs.getInt("status");
                    user = new User(userID, username, pwd, roleID, status);
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

    public static User getUser(String fullname, String password) {
        User user = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "SELECT userID, fullname, [password], roleID, [status] FROM [User]"
                        + " WHERE fullname=? and [password]=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, fullname);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                if (rs != null && rs.next()) {
                    String userID = rs.getString("userID");
                    int roleID = rs.getInt("roleID");
                    int status = rs.getInt("status");

                    user = new User(userID, fullname, password, roleID, status);
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
        System.out.println(getUser("Nguyen Thi Binh", "269853"));
    }
}
