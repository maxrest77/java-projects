import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class SmartAttendanceSystem {

    static final String DB_NAME = "attendance_db";
    static String URL;
    static String URL_WITHOUT_DB;
    static String USER;
    static String PASSWORD;

    public static void main(String[] args) {
        loadConfig(); // Load database credentials from config.properties
        URL_WITHOUT_DB = URL.replace(DB_NAME, ""); // For creating DB if it doesn't exist

        Scanner sc = new Scanner(System.in);

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection rootConn = DriverManager.getConnection(URL_WITHOUT_DB, USER, PASSWORD)) {
                setupDatabase(rootConn);
            } catch (SQLException e) {
                System.out.println("Error connecting to MySQL server: " + e.getMessage());
                System.out.println("Please ensure MySQL server is running and credentials are correct.");
                return;
            }

            // Connect to the specific database
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected to Database Successfully!");
                setupTables(conn);

                while (true) {
                    System.out.println("\n===== SMART ATTENDANCE SYSTEM =====");
                    System.out.println("1. Mark Attendance");
                    System.out.println("2. View Attendance");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");

                    int choice;
                    try {
                        choice = sc.nextInt();
                        sc.nextLine(); // consume newline
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number.");
                        sc.nextLine();
                        continue;
                    }

                    switch (choice) {
                        case 1 -> markAttendance(conn, sc);
                        case 2 -> viewAttendance(conn);
                        case 3 -> {
                            System.out.println("Exiting... Thank you!");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid choice! Try again.");
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            System.out.println("Ensure mysql-connector-j-9.4.0.jar is in your classpath.");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Load DB credentials from config.properties
    private static void loadConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
            System.exit(1);
        }
    }

    // Create database if it doesn't exist
    static void setupDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database setup completed successfully.");
        } catch (SQLException e) {
            System.out.println("Error setting up database: " + e.getMessage());
        }
    }

    // Create tables if they don't exist
    static void setupTables(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS attendance (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "student_name VARCHAR(100) NOT NULL," +
                    "roll_number VARCHAR(20) NOT NULL," +
                    "date DATE NOT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Tables setup completed successfully.");
        } catch (SQLException e) {
            System.out.println("Error setting up tables: " + e.getMessage());
        }
    }

    static void markAttendance(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: Student name cannot be empty.");
                return;
            }

            System.out.print("Enter Roll Number: ");
            String roll = sc.nextLine().trim();
            if (roll.isEmpty()) {
                System.out.println("Error: Roll number cannot be empty.");
                return;
            }

            String sql = "INSERT INTO attendance (student_name, roll_number, date, status) VALUES (?, ?, CURDATE(), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, roll);
                stmt.setString(3, "Present");

                int rows = stmt.executeUpdate();
                if (rows > 0) System.out.println("Attendance marked successfully for " + name);
                else System.out.println("Failed to mark attendance. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error marking attendance: " + e.getMessage());
        }
    }

    static void viewAttendance(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM attendance ORDER BY date DESC";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n--- Attendance Records ---");
                System.out.printf("%-10s %-20s %-15s %-15s %-10s%n", "ID", "Name", "Roll Number", "Date", "Status");
                System.out.println("----------------------------------------------------------------------");

                boolean hasRecords = false;
                while (rs.next()) {
                    hasRecords = true;
                    System.out.printf("%-10d %-20s %-15s %-15s %-10s%n",
                            rs.getInt("id"),
                            rs.getString("student_name"),
                            rs.getString("roll_number"),
                            rs.getDate("date"),
                            rs.getString("status"));
                }
                if (!hasRecords) System.out.println("No attendance records found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching attendance: " + e.getMessage());
        }
    }
}
