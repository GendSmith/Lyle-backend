package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3307;//debug模式是3307，开发模式是3306
    static String database = "Lyle";
    static String encoding = "UTF-8";
    static String user = "root";
    static String password = "zaozhizp2018";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException{
        System.out.println(getConnection());
    }
}
