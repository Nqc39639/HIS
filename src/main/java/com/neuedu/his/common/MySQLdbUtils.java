package com.neuedu.his.common;

import java.sql.*;

public class MySQLdbUtils {
    private static String user;
    private static String password;
    private static String dbName;
    private static String driver;
    private static String url;
    public static Connection conn;

    // 初始化参数
    static {
        user = "root";
        password = "password";
        dbName = "his_db";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        try {
            Class.forName(driver);   // 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //创建连接
    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    //创建Statement
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        return ps;
    }

    //关闭操作与连接
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}