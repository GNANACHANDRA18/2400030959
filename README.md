# Hibernate Department Management System

A Maven-based Hibernate project for managing Department entities with basic CRUD operations.

## Project Structure

```
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/klef/fsad/exam/
│   │   │       ├── Department.java        (Entity Class)
│   │   │       ├── HibernateUtil.java     (Utility Class)
│   │   │       └── ClientDemo.java        (Main Application)
│   │   └── resources/
│   │       └── hibernate.cfg.xml          (Hibernate Configuration)
```

## Prerequisites

- Java 8 or higher
- MySQL 5.7 or 8.0
- Maven 3.6 or higher

## Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE fsadexam;
```

2. Update the database connection in `src/main/resources/hibernate.cfg.xml`:
   - Change `hibernate.connection.username` (default: root)
   - Change `hibernate.connection.password` (default: root)

## Project Dependencies

The project uses the following key dependencies:
- Hibernate ORM 5.6.14
- MySQL Connector Java 8.0.33
- SLF4J 1.7.36
- JUnit 4.13.2

## Building the Project

1. Clean and build:
```bash
mvn clean install
```

2. Compile:
```bash
mvn compile
```

## Running the Application

1. Using Maven:
```bash
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

2. Or run directly from your IDE (Eclipse/IntelliJ):
   - Right-click `ClientDemo.java`
   - Select `Run As Java Application`

## Features

### Department Entity
- **ID**: Auto-generated primary key
- **Name**: Department name (required, max 100 chars)
- **Description**: Department description (optional, text)
- **Date**: Department creation date
- **Status**: Department status (Active/Inactive)
- **Location**: Department location
- **Budget**: Department budget

### Operations

#### 1. Insert Department
- Prompts user for department details
- Creates a new Department object
- Saves to database with auto-generated ID

#### 2. Delete Department
- Prompts user for Department ID
- Retrieves and deletes the department
- Shows confirmation message

#### 3. View All Departments
- Displays all departments in the database

#### 4. View Department by ID
- Searches for a specific department by ID

## Database Schema

The Hibernate ORM automatically creates the `departments` table with the following structure:

```sql
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    dept_date DATE,
    status VARCHAR(20),
    location VARCHAR(100),
    budget DOUBLE
);
```

## Troubleshooting

### "No suitable driver found" error
- Ensure MySQL connector JAR is in the classpath
- Run `mvn dependency:resolve`

### Connection refused error
- Ensure MySQL server is running
- Verify host, port, and credentials in `hibernate.cfg.xml`
- Check database exists: `mysql -u root -p fsadexam`

### Hibernate mapping error
- Ensure `Department.class` is mapped in `hibernate.cfg.xml`
- Check annotation syntax in `Department.java`

## Key Classes

### Department.java
Entity class with JPA annotations representing the Department table

### HibernateUtil.java
Utility class that manages SessionFactory creation and lifecycle

### ClientDemo.java
Main application class implementing menu-driven CRUD operations

## Notes

- Hibernate automatically creates tables if they don't exist (hbm2ddl.auto=update)
- All SQL queries are logged to console for debugging
- Transaction management ensures data consistency
- Exception handling prevents data corruption on errors

## Author
Created for FSAD Exam - Department Management System
