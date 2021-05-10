package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890Ds!";

    public Connection getConnection() {
        try (Connection oConnection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            return oConnection;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
