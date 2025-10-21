import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StudentRegistrationForm extends JFrame implements ActionListener {
    // Form fields
    private JTextField nameField, rollField, emailField, deptField;
    private JRadioButton maleBtn, femaleBtn;
    private JButton submitBtn, clearBtn;
    private ButtonGroup genderGroup;

    // JDBC variables
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "studentdb";
    private static final String DB_USER = "";        // change if needed
    private static final String DB_PASSWORD = "";         // your MySQL password

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel heading = new JLabel("Student Registration", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(30, 20, 320, 40);
        add(heading);

        // Labels and fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 90, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 90, 180, 25);
        add(nameField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(50, 130, 100, 25);
        add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(150, 130, 180, 25);
        add(rollField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 170, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 170, 180, 25);
        add(emailField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(50, 210, 100, 25);
        add(deptLabel);

        deptField = new JTextField();
        deptField.setBounds(150, 210, 180, 25);
        add(deptField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 250, 100, 25);
        add(genderLabel);

        maleBtn = new JRadioButton("Male");
        maleBtn.setBounds(150, 250, 70, 25);
        femaleBtn = new JRadioButton("Female");
        femaleBtn.setBounds(230, 250, 80, 25);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);

        add(maleBtn);
        add(femaleBtn);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(80, 320, 100, 35);
        submitBtn.setBackground(new Color(50, 205, 50));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        submitBtn.addActionListener(this);
        add(submitBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(200, 320, 100, 35);
        clearBtn.setBackground(new Color(220, 20, 60));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            registerStudent();
        } else if (e.getSource() == clearBtn) {
            clearForm();
        }
    }

    private void registerStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String email = emailField.getText().trim();
        String dept = deptField.getText().trim();
        String gender = maleBtn.isSelected() ? "Male" : (femaleBtn.isSelected() ? "Female" : "");

        // Validation
        if (name.isEmpty() || roll.isEmpty() || dept.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert into database
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO students (name, roll_no, email, department, gender) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, email);
            stmt.setString(4, dept);
            stmt.setString(5, gender);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student registered successfully!");
            clearForm();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Roll number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        rollField.setText("");
        emailField.setText("");
        deptField.setText("");
        genderGroup.clearSelection();
    }

    public static void main(String[] args) {
        try {
            // Register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create database and table if they don't exist
            setupDatabase();
            
            SwingUtilities.invokeLater(() -> new StudentRegistrationForm());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL Driver not found! Make sure mysql-connector-j-9.4.0.jar is in the classpath.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create database if not exists
            PreparedStatement createDbStmt = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            createDbStmt.execute();
            
            // Connect to the database
            try (Connection dbConn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
                // Create table if not exists
                String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "roll_no VARCHAR(20) NOT NULL UNIQUE," +
                    "email VARCHAR(100)," +
                    "department VARCHAR(50) NOT NULL," +
                    "gender VARCHAR(10) NOT NULL" +
                    ")";
                PreparedStatement createTableStmt = dbConn.prepareStatement(createTableSQL);
                createTableStmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database setup failed: " + e.getMessage(), e);
        }
    }
}
