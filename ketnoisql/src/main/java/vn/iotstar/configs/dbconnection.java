package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {

    private final String serverName = "localhost";
    private final String dbName = "MyWebAppDB";
    private final String portNumber = "1433";
    private final String user = "sa";                // user SQL Server
    private final String password = "di0988800705";  // mật khẩu SQL Server

    public Connection getConnectionW() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                   + ";databaseName=" + dbName
                   + ";encrypt=true;trustServerCertificate=true;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
}
