import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database URL, username, and password for connecting to MySQL database.
    // Update these values with your own database credentials
    private static final String DB_URL = "jdbc:mysql://db:3306/bankdb";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    /**
     * Establishes a connection to the MySQL database.
     * 
     * @return a Connection object for interacting with the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
