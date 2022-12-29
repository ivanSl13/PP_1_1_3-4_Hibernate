package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() { }


    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String string = "CREATE TABLE IF NOT EXISTS UserTable(Id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, secondName VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(string);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS userTable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlAddUser = "INSERT INTO userTable(name, secondName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAddUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User c именем %s %s добавлен в базу данных \n", name, lastName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlDelUser = "DELETE FROM userTable WHERE Id = ?";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelUser);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("secondName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("Id"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().executeUpdate("TRUNCATE TABLE userTable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
