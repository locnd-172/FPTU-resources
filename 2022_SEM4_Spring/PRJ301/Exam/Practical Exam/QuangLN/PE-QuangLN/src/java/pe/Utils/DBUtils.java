/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DBUtils {
    
    public static Connection makeConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost\\PIPI/DOLPHIN:1433;databaseName=TestFinal;user=sa;password=123456";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        
        Class.forName(driver);
        Connection cn = DriverManager.getConnection(url);
        return cn;
    }
    
   
}
