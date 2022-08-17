/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import locnd.DTO.Food;
import locnd.Utils.DBUtils;

/**
 *
 * @author Admin
 */
public class FoodDAO {

    public static ArrayList<Food> getFoods() {
        Connection cn = null;
        ArrayList<Food> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id, name, description, price, cookingTime, status FROM [tblFoods]";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String foodID = rs.getString("id");
                    String foodName = rs.getString("name");
                    String desc = rs.getString("description");
                    float price = rs.getFloat("price");
                    int cTime = rs.getInt("CookingTime");
                    int status = rs.getInt("Status");
                    Food food = new Food(foodID, foodName, desc, price, cTime, status);
                    list.add(food);
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
    
    public static boolean updateFoodStatus(String foodID, int newStatus) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [tblFoods] SET status=? WHERE id=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, newStatus);
                pst.setString(2, foodID);
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
        System.out.println(updateFoodStatus("F-001", 1));
    }

    public static ArrayList<Food> getFoodsByName(String keyword) {
        ArrayList<Food> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id, name, description, price, cookingTime, status FROM [tblFoods] "
                        + "WHERE name LIKE ? and [status]=1";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        String foodID = rs.getString("id");
                        String foodName = rs.getString("name");
                        String desc = rs.getString("description");
                        float price = rs.getFloat("price");
                        int cTime = rs.getInt("CookingTime");
                        int status = rs.getInt("Status");
                        Food food = new Food(foodID, foodName, desc, price, cTime, status);
                        list.add(food);
                    }
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
}
