import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n=== 📚 LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine();
                        library.addBook(title, author);
                    }
                    case 2 -> {
                        System.out.println("\n--- All Books ---");
                        List<Book> books = library.viewBooks();
                        if (books.isEmpty()) {
                            System.out.println("No books available in the library.");
                        } else {
                            for (Book b : books) System.out.println(b);
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Book ID to issue: ");
                        try {
                            int id = Integer.parseInt(sc.nextLine());
                            library.issueBook(id);
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Invalid ID format. Please enter a number.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Book ID to return: ");
                        try {
                            int id = Integer.parseInt(sc.nextLine());
                            library.returnBook(id);
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Invalid ID format. Please enter a number.");
                        }
                    }
                case 5 -> {
                        System.out.println("👋 Exiting system...");
                        sc.close();
                        DatabaseConnection.closeConnection();
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number for your choice.");
            } catch (Exception e) {
                System.out.println("❌ An error occurred: " + e.getMessage());
            }
            }
        }
    }
