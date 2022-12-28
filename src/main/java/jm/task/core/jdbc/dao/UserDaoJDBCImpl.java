package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() { }

    public void createUsersTable() {
        try (Connection con = Util.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS UserTable(Id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, secondName VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";
            Statement ps = con.createStatement();
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection()) {
            con.createStatement().executeUpdate("DROP TABLE IF EXISTS userTable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sdd = "INSERT INTO userTable(name, secondName, age) VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";
        try (Connection con = Util.getConnection()) {
            con.createStatement().executeUpdate(sdd);
            System.out.printf("User c именем %s %s добавлен в базу данных \n", name, lastName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection()) {
            con.createStatement().executeUpdate("DELETE FROM userTable WHERE Id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = Util.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM userTable");
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("secondName"), rs.getByte("age"));
                user.setId(rs.getLong("Id"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection()) {
            con.createStatement().executeUpdate("TRUNCATE TABLE userTable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
