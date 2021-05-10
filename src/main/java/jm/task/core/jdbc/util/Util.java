package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "1234567890Ds!";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection connection = null;

    public Util() {}

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
