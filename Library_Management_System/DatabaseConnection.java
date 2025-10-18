import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.File;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load JDBC Driver
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("❌ MySQL JDBC Driver not found");
                    e.printStackTrace();
                    System.exit(1);
                }
                
                // Load DB properties
                Properties props = new Properties();
                File configFile = new File("config.properties");
                if (!configFile.exists()) {
                    System.out.println("❌ config.properties file not found");
                    System.exit(1);
                }
                props.load(new FileInputStream(configFile));

                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                if (url == null || username == null || password == null) {
                    System.out.println("❌ Missing database configuration in config.properties");
                    System.exit(1);
                }

                // Connect to MySQL
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Connected to MySQL database");

                // Auto-create books table if it doesn't exist
                try (Statement st = connection.createStatement()) {
                    st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS books (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "title VARCHAR(100) NOT NULL," +
                        "author VARCHAR(100) NOT NULL," +
                        "isIssued BOOLEAN DEFAULT FALSE" +
                        ")"
                    );
                }
            } catch (IOException e) {
                System.out.println("❌ Failed to read config.properties");
                e.printStackTrace();
                System.exit(1);
            } catch (SQLException e) {
                System.out.println("❌ Failed to connect to MySQL: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Database connection closed");
            } catch (SQLException e) {
                System.out.println("❌ Failed to close database: " + e.getMessage());
            }
        }
    }
}
