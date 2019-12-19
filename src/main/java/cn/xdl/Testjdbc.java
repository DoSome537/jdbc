package cn.xdl;

import java.sql.*;

public class Testjdbc {

    public static void main(String[] args) {
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://39.105.89.193:3306?useSSL=false&serverTimezone=Asia/Shanghai";
        String DB_URL1 = "jdbc:mysql://39.105.89.193:3306/practice?useSSL=false&serverTimezone=Asia/Shanghai";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "1234";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Connection conn = DriverManager.getConnection(DB_URL1, DB_USERNAME, DB_PASSWORD);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `ELEMENT`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
            Statement statement = connection.createStatement();
            statement.execute("USE `practice`");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `ELEMENT`");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("ELEMENT_ID"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
