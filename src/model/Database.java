package model;

import java.sql.*;

public class Database {
    public static Connection open() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            return null ;
        }
    }
}