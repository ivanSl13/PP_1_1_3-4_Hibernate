package jm.task.core.jdbc;



import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
        List<User> user = userService.getAllUsers();
        user.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.saveUser("Vasa2", "Vasin2", (byte) 30);
        userService.saveUser("Vasa3", "Vasin3", (byte) 40);

    }
}
