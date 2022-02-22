package com.ct.myssm.basedao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
* @Description: 获取连接工具类
* @Param:
* @return:
* @Author: CT
* @Date: 2022/1/6 11:04
*/
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection createConn() {

        InputStream is = null;
        try {
            is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            Class.forName(driverClass);
            return DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn==null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }
    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn ==null){
            return;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
