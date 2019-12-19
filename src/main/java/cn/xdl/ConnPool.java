package cn.xdl;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnPool {
    static final int MIN_IDLE = 10;
    static final int MAX_IDLE = 100;
    DBUtil dbUtil = new DBUtil();
    public ConnPool(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }
    public void init(){
        for (int i = 0; i < MIN_IDLE ; i++) {
            new Thread(dbUtil).start();
            dbUtil.connList.add(DBUtil.conn);
        }
        new Thread(() -> {
            if (dbUtil.connList.size() < MIN_IDLE){
                new Thread(dbUtil).start();
                dbUtil.connList.add(DBUtil.conn);
            }
            if (dbUtil.connList.size() > MAX_IDLE){
                try {
                    dbUtil.connList.get(0).close();
                    dbUtil.connList.remove(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public Connection getConn(){
        Connection conn = dbUtil.connList.get(0);
        dbUtil.connList.remove(0);
        return conn;
    }
}
