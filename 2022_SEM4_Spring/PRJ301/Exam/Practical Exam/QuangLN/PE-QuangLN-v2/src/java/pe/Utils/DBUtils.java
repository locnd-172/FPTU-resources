package pe.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBUtils {

    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String url = "jdbc:sqlserver://localhost\\PIPI\\DOLPHIN:1433;databaseName=TestFinal;user=sa;password=123456";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }

}
