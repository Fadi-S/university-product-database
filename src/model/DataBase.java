package model;

import java.sql.*;

public class DataBase {
    public static Connection open() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
            return connection;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
          return null ;
        }

    }
}