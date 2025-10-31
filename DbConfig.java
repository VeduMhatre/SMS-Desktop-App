import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading db.properties: " + e.getMessage());
            // Exit the application if config cannot be loaded
            System.exit(1); 
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }

    public static String getUser() {
        return props.getProperty("db.user");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }
    
    // Optional: A centralized method to get a connection
    public static Connection getConnection() throws SQLException {
        // Ensure driver is registered only if necessary (Modern JDBC often handles this)
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); 
        return DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }
}