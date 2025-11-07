package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/phonebook_db";
    private static final String USER = "volkan";
    private static final String PASSWORD = "123";

    /**
     * Establishes and returns a connection to the database.
     * @return Connection object if successful, null otherwise.
     */
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }
}
