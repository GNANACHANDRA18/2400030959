package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.Scanner;

/**
 * ClientDemo Class - Implements Department CRUD operations
 * Operations: Insert, Delete based on ID
 */
public class ClientDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        boolean running = true;

        System.out.println("===== Department Management System =====");

        while (running) {
            System.out.println("\n1. Insert Department");
            System.out.println("2. Delete Department");
            System.out.println("3. View All Departments");
            System.out.println("4. View Department by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    insertDepartment(factory, scanner);
                    break;
                case 2:
                    deleteDepartment(factory, scanner);
                    break;
                case 3:
                    viewAllDepartments(factory);
                    break;
                case 4:
                    viewDepartmentById(factory, scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
    }

    /**
     * Insert a new Department record into the database
     */
    public static void insertDepartment(SessionFactory factory, Scanner scanner) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("\n----- Insert New Department -----");
            System.out.print("Enter Department Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Department Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Department Location: ");
            String location = scanner.nextLine();

            System.out.print("Enter Department Status (Active/Inactive): ");
            String status = scanner.nextLine();

            System.out.print("Enter Department Budget: ");
            double budget = scanner.nextDouble();

            // Create Department object
            Department department = new Department();
            department.setName(name);
            department.setDescription(description);
            department.setLocation(location);
            department.setStatus(status);
            department.setBudget(budget);
            department.setDeptDate(new Date());

            // Save to database
            session.save(department);
            transaction.commit();

            System.out.println("✓ Department inserted successfully!");
            System.out.println("Department Details: " + department);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("✗ Error inserting department: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Delete a Department record based on ID
     */
    public static void deleteDepartment(SessionFactory factory, Scanner scanner) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("\n----- Delete Department -----");
            System.out.print("Enter Department ID to delete: ");
            int deptId = scanner.nextInt();

            // Retrieve the department
            Department department = session.get(Department.class, deptId);

            if (department != null) {
                session.delete(department);
                transaction.commit();
                System.out.println("✓ Department with ID " + deptId + " deleted successfully!");
                System.out.println("Deleted Department: " + department);
            } else {
                System.out.println("✗ Department with ID " + deptId + " not found!");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("✗ Error deleting department: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * View all Department records
     */
    public static void viewAllDepartments(SessionFactory factory) {
        Session session = factory.openSession();

        try {
            System.out.println("\n----- All Departments -----");
            java.util.List<Department> departments = session
                    .createQuery("FROM Department", Department.class)
                    .list();

            if (departments.isEmpty()) {
                System.out.println("No departments found in database.");
            } else {
                for (Department dept : departments) {
                    System.out.println(dept);
                }
                System.out.println("Total Departments: " + departments.size());
            }

        } catch (Exception e) {
            System.out.println("✗ Error retrieving departments: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * View a Department record by ID
     */
    public static void viewDepartmentById(SessionFactory factory, Scanner scanner) {
        Session session = factory.openSession();

        try {
            System.out.println("\n----- View Department by ID -----");
            System.out.print("Enter Department ID: ");
            int deptId = scanner.nextInt();

            Department department = session.get(Department.class, deptId);

            if (department != null) {
                System.out.println("Department Found:");
                System.out.println(department);
            } else {
                System.out.println("✗ Department with ID " + deptId + " not found!");
            }

        } catch (Exception e) {
            System.out.println("✗ Error retrieving department: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
