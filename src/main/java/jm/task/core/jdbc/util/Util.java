package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890Ds!";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection = null;

    public Util() {
        if(this.connection == null) {
            try {
                Class.forName(DRIVER);
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    this.connection = connection;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
