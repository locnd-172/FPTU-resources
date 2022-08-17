/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnd.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DBUtils {
    
    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String url = "jdbc:sqlserver://localhost\\PIPI\\DOLPHIN:1433;databaseName=FoodManagement;user=sa;password=123456";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(makeConnection());
    }
}
