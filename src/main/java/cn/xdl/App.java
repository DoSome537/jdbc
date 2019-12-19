package cn.xdl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    static BasicDataSource bs = null;
    static Properties properties = new Properties();

    static {
        InputStream is = App.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
            bs = (BasicDataSource) BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        for (int i = 0; i <50 ; i++) {
            try {
                Connection conn = bs.getConnection();
                String sql = "SELECT * FROM `ELEMENT`";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()){
                    System.out.print(resultSet.getString(1)+"-");
                    System.out.println(resultSet.getString(2));
                }
                resultSet.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
