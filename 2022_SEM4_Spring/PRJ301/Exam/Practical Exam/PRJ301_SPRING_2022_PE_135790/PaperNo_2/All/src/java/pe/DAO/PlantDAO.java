package pe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.DTO.Plant;
import pe.utils.DBUtils;

/**
 *
 * @author Loc NgD <locndse160199@fpt.edu.vn>
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String name) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT PID, Pname, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "FROM Plants JOIN Categories ON Plants.CateID = Categories.CateID\n";
                sql = sql + "WHERE Plants.PName LIKE ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String pname = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, pname, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Plant getPlantById(int pid) {
        Plant plant = null;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "FROM Plants JOIN Categories ON Plants.CateID = Categories.CateID WHERE PID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    String pname = rs.getString("PName");
                    int price = rs.getInt("price");
                    String image = rs.getString("imgPath");
                    String desc = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    plant = new Plant(pid, pname, price, image, desc, status, cateid, catename);
                }
            }
        } catch (Exception e) {
            System.out.println("Error connection");
        }
        return plant;
    }

    public static boolean updatePlant(int id, int status) {
        try {
            Plant plant = getPlantById(id);
            if (plant == null) {
                return false;
            }
            plant.setStatus(status);
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Plants SET [status]=?"
                        + " WHERE Plants.PID=?";

                PreparedStatement pst = cn.prepareStatement(sql);

                pst.setInt(1, status);
                pst.setInt(2, id);

                int check = pst.executeUpdate();
                System.out.println(check);
                cn.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean res = updatePlant(8, 1);
        System.out.println(res);
    }
}
