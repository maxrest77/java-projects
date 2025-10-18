# 1.Smart Student Management / Smart Attendance System

A simple **Java-based Smart Attendance System** using MySQL for storing attendance records.

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
javac -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" SmartAttendanceSystem.java

# Run
java -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" SmartAttendanceSystem

📝** Features **

Create database and tables automatically if they don’t exist

Mark attendance for students (name + roll number)

View attendance records with date and status

Input validation for empty names or roll numbers

Uses MySQL JDBC driver

**Security**

Do not commit config.properties with passwords

Add your database credentials locally and make sure .gitignore includes it

2.📚 Library Management System

A simple console-based Library Management System in Java with MySQL integration.
Supports adding books, viewing books, issuing and returning books.
Prerequisites

Java JDK 17+ installed

MySQL server running

MySQL Connector/J (placed in project folder)

Database Setup

Login to MySQL:

mysql -u root -p


Create the database:

CREATE DATABASE IF NOT EXISTS library_db;


Ensure credentials in config.properties match your MySQL setup:

db.url=jdbc:mysql://localhost:3306/library_db
db.username=root
db.password=YourPassword


Note: config.properties is gitignored to protect credentials.

How to Run
PowerShell / Windows

Open PowerShell in the project folder:

cd "C:\java projects\Library_Management_System"


Compile all Java files:

javac -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" *.java


Run the program:

java -cp ".;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar" Main

Command Prompt (CMD)
cd "C:\java projects\Library_Management_System"
javac -cp .;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar *.java
java -cp .;mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar Main

Features

Add Book – Add a new book with title and author.

View Books – List all books with their status (Available/Issued).

Issue Book – Issue a book by ID (prevents double issuance).

Return Book – Return a book by ID.

Exit – Safely exit the program.

**Author**

Karthikeyan S
SRM Institute of Science and Technology
