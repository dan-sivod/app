package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util = new Util();
    private Connection connection = null;

    public UserDaoJDBCImpl() {
        try {
            this.connection = util.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery("CREATE TABLE IF NOT EXISTS users(" +
                    "id integer primary key auto_increment, " +
                    "name varchar(100), " +
                    "lastName varchar(100), " +
                    "age int);");
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery("DROP TABLE IF EXISTS users;");
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        long id = 1;
        String strId = "SELECT * FROM users ORDER BY id DESC LIMIT 1;";
        String querySaveUser = "INSERT INTO users (id, name, lastname, age) VALUES (?,?,?,?)";

        try(Statement statement = connection.createStatement();
            PreparedStatement prepareStatement = connection.prepareStatement(querySaveUser)) {
            ResultSet oResultSet = statement.executeQuery(strId);
            while(!oResultSet.next()) {
                id = oResultSet.getInt("id") + 1;
            }
            prepareStatement.setLong(1, id);
            prepareStatement.setString(2, name);
            prepareStatement.setString(3, lastName);
            prepareStatement.setInt(4, age);
            prepareStatement.execute();
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String queryRemoveUserId = "DELETE FROM users WHERE id = " + id + ";";

        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(queryRemoveUserId);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet oResultSet = statement.executeQuery("SELECT * FROM users;");
            while(!oResultSet.next()) {
                userList.add(new User(oResultSet.getString("name"),
                                    oResultSet.getString("lastName"),
                                    oResultSet.getByte("age")));
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery("TRUNCATE TABLE users;");
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
