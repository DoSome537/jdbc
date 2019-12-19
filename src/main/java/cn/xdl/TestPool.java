package cn.xdl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPool {

    public static void main(String[] args) {
        Connection conn;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = DBUtil.getConn();
        String sql = "SELECT * from ELEMENT";

        try {
            ps = conn.prepareStatement(sql);
            ps.execute("USE `practice`");
            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
