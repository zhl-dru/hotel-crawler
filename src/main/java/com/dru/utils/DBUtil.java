package com.dru.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    // 加载配置文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("mysql-config");

   
    static {
        URL = resourceBundle.getString("jdbc.url");
        USERNAME = resourceBundle.getString("jdbc.username");
        PASSWORD = resourceBundle.getString("jdbc.password");
        String driverClassName = resourceBundle.getString("jdbc.driverClassName");

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 得到数据库链接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    // 关闭数据库链接
    public static void closeConnection(ResultSet rs, PreparedStatement statement, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
