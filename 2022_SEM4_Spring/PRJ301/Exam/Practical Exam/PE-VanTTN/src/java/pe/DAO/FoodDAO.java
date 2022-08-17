/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.DTO.Food;
import pe.Utils.DBUtils;

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
                String sql = "SELECT FoodID, FoodName, CookingTime, Status FROM Food";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next() && rs != null) {
                    String foodID = rs.getString("FoodID");
                    String foodName = rs.getString("FoodName");
                    int cTime = rs.getInt("CookingTime");
                    int status = rs.getInt("Status");
                    Food food = new Food(foodID, foodName, cTime, status);
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

    public static Food getFoodByID(String foodID) {
        Connection cn = null;
        Food food = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT FoodID, FoodName, CookingTime, Status FROM Food WHERE FoodID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, foodID);
                ResultSet rs = pst.executeQuery();

                while (rs.next() && rs != null) {
                    String foodName = rs.getString("FoodName");
                    int cTime = rs.getInt("CookingTime");
                    int status = rs.getInt("Status");
                    food = new Food(foodID, foodName, cTime, status);
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
        return food;
    }

    public static boolean insertFood(String foodID, String foodName, int cTime, int status) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO Food(FoodID, FoodName, CookingTime, Status) VALUES(?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, foodID);
                pst.setString(2, foodName);
                pst.setInt(2, cTime);
                pst.setInt(3, status);
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

    public static boolean updateFood(String foodID, String newFoodName, int newCTime, int newStatus) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Food SET FoodName=?, CookingTime=?, Status=? WHERE FoodId=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newFoodName);
                pst.setInt(2, newCTime);
                pst.setInt(3, newStatus);
                pst.setString(4, foodID);
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
    
    public static boolean updateFoodStatus(String foodID, int newStatus) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Food SET Status=? WHERE FoodId=?";
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

    public static boolean deleteFood(String foodID) {
        Connection cn = null;
        int res = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM Food WHERE FoodID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, foodID);
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

    public static ArrayList<Food> getFoodsByName(String keyword) {
        ArrayList<Food> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT FoodID, FoodName, CookingTime, Status FROM Food WHERE FoodName LIKE ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        String foodID = rs.getString("FoodID");
                        String foodName = rs.getString("FoodName");
                        int cTime = rs.getInt("CookingTime");
                        int status = rs.getInt("Status");
                        Food food = new Food(foodID, foodName, cTime, status);
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
