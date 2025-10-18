import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private Connection conn;

    public Library() {
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Database connection failed. Exiting...");
            System.exit(1);
        }
        // Auto-create table if it doesn't exist
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS books (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(100) NOT NULL," +
                "author VARCHAR(100) NOT NULL," +
                "isIssued BOOLEAN DEFAULT FALSE" +
                ")"
            );
        } catch (SQLException e) {
            System.out.println("❌ Failed to create books table.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void addBook(String title, String author) {
        String query = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add book.");
            e.printStackTrace();
        }
    }

    public List<Book> viewBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("isIssued")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch books.");
            e.printStackTrace();
        }
        return books;
    }

    public void issueBook(int bookId) {
        String check = "SELECT isIssued FROM books WHERE id = ?";
        String update = "UPDATE books SET isIssued = TRUE WHERE id = ?";
        try (PreparedStatement ps1 = conn.prepareStatement(check);
             PreparedStatement ps2 = conn.prepareStatement(update)) {
            ps1.setInt(1, bookId);
            ResultSet rs = ps1.executeQuery();
            if (rs.next() && rs.getBoolean("isIssued")) {
                System.out.println("⚠️ Book already issued!");
                return;
            }
            ps2.setInt(1, bookId);
            int rows = ps2.executeUpdate();
            if (rows > 0) System.out.println("📕 Book issued successfully!");
            else System.out.println("❌ Book not found!");
        } catch (SQLException e) {
            System.out.println("❌ Failed to issue book.");
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId) {
        String update = "UPDATE books SET isIssued = FALSE WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setInt(1, bookId);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("📗 Book returned successfully!");
            else System.out.println("❌ Book not found!");
        } catch (SQLException e) {
            System.out.println("❌ Failed to return book.");
            e.printStackTrace();
        }
    }
}
