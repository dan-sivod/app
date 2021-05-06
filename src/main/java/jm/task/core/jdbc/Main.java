package jm.task.core.jdbc;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890Ds!";

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        try (Connection oConnection = DriverManager.getConnection(URL,USER,PASSWORD);
             Statement oStatement = oConnection.createStatement()) {
            oStatement.execute("insert into users (name, city) values ('Timi', 'Novgorod')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
