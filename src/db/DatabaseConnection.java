// filepath: /Users/samvi/Documents/GitHub/CAB-System/src/db/DatabaseConnection.java
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, username, password);
    }
}