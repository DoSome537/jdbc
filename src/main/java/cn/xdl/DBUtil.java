package cn.xdl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DBUtil implements Runnable {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://39.105.89.193:3306?useSSL=false&serverTimezone=Asia/Shanghai";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "1234";
    static Connection conn;
    Vector<Connection> connList = new Vector<>();
    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        getConn();
        connList.add(conn);
    }


}
