import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database URL, username, and password for connecting to MySQL database.
    // Update these values with your own database credentials
    private static final String DB_URL = "jdbc:mysql://<YOUR_AZURE_DATABASE_HOST>/<YOUR_DATABASE_NAME>";
    private static final String USER = "<YOUR_DATABASE_USERNAME>";
    private static final String PASSWORD = "<YOUR_DATABASE_PASSWORD>";

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
