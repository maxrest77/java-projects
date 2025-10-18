# Java Projects Collection For Advanced Programming Practice

This repository contains multiple Java projects, each with its own functionality, database requirements, and dependencies.

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

2.Library Management System

A simple console-based Library Management System in Java with MySQL integration.
Supports adding books, viewing books, issuing and returning books.

Features

Add Book – Add a new book with title and author.

View Books – List all books with their status (Available/Issued).

Issue Book – Issue a book by ID (prevents double issuance).

Return Book – Return a book by ID.

Exit – Safely exit the program.


**Author**

Karthikeyan S
SRM Institute of Science and Technology
