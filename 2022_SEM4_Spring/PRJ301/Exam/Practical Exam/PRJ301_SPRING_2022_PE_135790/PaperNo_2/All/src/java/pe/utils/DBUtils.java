package pe.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Hoa Doan
 */
public class DBUtils {

    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "PIPI/DOLPHIN";
        String port = "1433";
        String uid = "sa";
        String pwd = "123456";
        String db = "PlantShopPE";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);

        return cn;
    }

    public static void main(String[] args) throws Exception {
        Connection cn = makeConnection();
        if (cn != null) {
            System.out.println("OK");
        } else {
            System.out.println("FFF");
        }
    }
}
