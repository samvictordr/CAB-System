package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://<YOUR_AZURE_DATABASE_HOST>/<YOUR_DATABASE_NAME>";
    private static final String USER = "<YOUR_DATABASE_USERNAME>";
    private static final String PASSWORD = "<YOUR_DATABASE_PASSWORD>";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}