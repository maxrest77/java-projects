# Java Projects Collection For Advanced Programming Practice
4 Projects in 1 folder:

This repository contains multiple Java projects, each with its own functionality, database requirements, and dependencies.

## 🧰 Tech Stack(Used for all Projects)

| Component | Technology |
|------------|-------------|
| **Frontend (GUI)** | Java Swing |
| **Backend** | Java (JDBC) |
| **Database** | MySQL |
| **IDE (optional)** | IntelliJ IDEA / Eclipse / VS Code |

---

## ⚙️ Prerequisites

1. **Java JDK** installed (version 11+ recommended)
2. **MySQL Server** installed and running
3. **MySQL Connector/J** (`mysql-connector-j-9.4.0.jar`) present in the project folder
4. Update your **`config.properties`** with your database credentials:

**Properties**
db.url=jdbc:mysql://localhost:3306/attendance_db?useSSL=false&serverTimezone=UTC
db.user=root
db.password=YOUR_PASSWORD_HERE

# Compile
javac -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" #Your_file_name.java

# Run
java -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" #ur file name

** Projects Included**


**1. Smart Attendance System**

Track student attendance

Automatically creates database and tables if needed

Input validation for student name and roll number

MySQL database integration

Output:

<img width="200" height="300" alt="image" src="https://github.com/user-attachments/assets/92e6c18e-5850-4113-96e6-378166992551" />



2.Library Management System

A simple console-based Library Management System in Java with MySQL integration.
Supports adding books, viewing books, issuing and returning books.

Features

Add Book – Add a new book with title and author.

View Books – List all books with their status (Available/Issued).

Issue Book – Issue a book by ID (prevents double issuance).

Return Book – Return a book by ID.

Exit – Safely exit the program.

Output:

<img width="200" height="300" alt="image" src="https://github.com/user-attachments/assets/571107cb-17bd-49cd-9ffe-6ca10e473f2f" /> <img width="200" height="300" alt="image" src="https://github.com/user-attachments/assets/e910ee19-efa6-4c66-ae4b-f3f9a6dd72c3" />


3. Student Registration Form (Java Swing + MySQL)

A simple and interactive **Java Swing GUI application** that allows users to register students by entering details such as Name, Roll Number, Email, Department, and Gender.  
All data is **validated** and securely **stored in a MySQL database** using JDBC.

---
 Features

-  Built with **Java Swing** for GUI  
-  Input validation for required fields and email format  
-  Data stored in **MySQL** using JDBC  
-  Clear button to reset form fields  
-  Handles duplicate roll numbers and invalid data gracefully  
-  Professional UI with basic color theme  

Output:

<img width="200" height="300" alt="image" src="https://github.com/user-attachments/assets/464c8b1a-39e9-4444-a452-e2bd7b5667ff" />




4. Calculator App (Java Swing)

A simple, user-friendly **Calculator application** built using **Java Swing**.  
Performs basic arithmetic operations such as addition, subtraction, multiplication, and division through an interactive GUI.

---

**Features**

- ➕ Addition  
- ➖ Subtraction  
- ✖️ Multiplication  
- ➗ Division (with division-by-zero check)  
- 🔄 Clear (`C`) and Backspace (`←`) buttons  
- 💡 Supports decimal numbers  
- 🪟 Modern and minimal GUI (Swing-based)  
- 🔁 Continuous operation chaining (e.g., 5 + 3 = 8 → +2 = 10)

Output:

<img width="200" height="300" alt="image" src="https://github.com/user-attachments/assets/2fc3da70-04a4-4be7-9e5c-8fd57e1ca3ad" />

**Author**

Karthikeyan S
SRM Institute of Science and Technology
