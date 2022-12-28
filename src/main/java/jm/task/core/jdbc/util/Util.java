package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/store";
    public static final String DB_Admin_Password = "root";

//    public static SessionFactory sessionFactory

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_Admin_Password, DB_Admin_Password);
//            System.out.println("Connection on");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
