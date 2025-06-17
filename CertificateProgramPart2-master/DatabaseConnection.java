import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The DatabaseConnection class provides methods for establishing a connection to a MySQL database.
 */
public class DatabaseConnection {
    /**
     * Constructs a new DatabaseConnection object.
     */
    public DatabaseConnection() {
        // Constructor implementation
    }
    /** The static Connection object representing the connection to the database. */
    public static Connection databaseLink;
    /**
     * Attempts to establish a connection to the MySQL database specified by the database name,
     * username, and password.
     *
     * @return The Connection object representing the established database connection.
     */
    public static Connection getConnection() {
        String databaseName = "certificateprogram"; // Name of the database
        String databaseUser = "root"; // Username for the database
        String databasePassword = ""; // Password for the database
        String url = "jdbc:mysql://localhost:3306/" + databaseName; // Database URL

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during connection establishment
            e.printStackTrace();
        }
        // Return the established database connection
        return databaseLink;
    }
}