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

**Author**

Karthikeyan S
SRM Institute of Science and Technology
