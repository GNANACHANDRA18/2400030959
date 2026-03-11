package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * DataLoader Class - Auto-loads test data and demonstrates CRUD operations
 */
public class DataLoader {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        System.out.println("\n========================================");
        System.out.println("   HIBERNATE DEPARTMENT MANAGEMENT");
        System.out.println("========================================\n");

        try {
            // Insert test departments
            System.out.println("1. INSERTING TEST DEPARTMENTS...\n");
            int dept1 = insertDepartment(factory, "Information Technology", 
                    "IT Department", "Building A", "Active", 50000);
            System.out.println("✓ Department 1 inserted with ID: " + dept1);
            
            int dept2 = insertDepartment(factory, "Human Resources", 
                    "HR Department", "Building B", "Active", 30000);
            System.out.println("✓ Department 2 inserted with ID: " + dept2);
            
            int dept3 = insertDepartment(factory, "Finance", 
                    "Finance Department", "Building C", "Active", 40000);
            System.out.println("✓ Department 3 inserted with ID: " + dept3);

            // Display all departments
            System.out.println("\n2. VIEWING ALL DEPARTMENTS...\n");
            displayAllDepartments(factory);

            // Delete a department
            System.out.println("\n3. DELETING DEPARTMENT WITH ID: " + dept2 + "...\n");
            deleteDepartment(factory, dept2);
            System.out.println("✓ Department " + dept2 + " deleted successfully");

            // Display remaining departments
            System.out.println("\n4. VIEWING REMAINING DEPARTMENTS...\n");
            displayAllDepartments(factory);

            System.out.println("\n========================================");
            System.out.println("      TEST COMPLETED SUCCESSFULLY");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    /**
     * Insert Department
     */
    private static int insertDepartment(SessionFactory factory, String name, 
            String description, String location, String status, double budget) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int deptId = 0;

        try {
            transaction = session.beginTransaction();

            Department department = new Department();
            department.setName(name);
            department.setDescription(description);
            department.setLocation(location);
            department.setStatus(status);
            department.setBudget(budget);
            department.setDeptDate(new Date());

            deptId = (Integer) session.save(department);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deptId;
    }

    /**
     * Delete Department
     */
    private static void deleteDepartment(SessionFactory factory, int deptId) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);

            if (department != null) {
                session.delete(department);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Display All Departments
     */
    private static void displayAllDepartments(SessionFactory factory) {
        Session session = factory.openSession();

        try {
            List<Department> departments = session
                    .createQuery("FROM Department", Department.class)
                    .list();

            if (departments.isEmpty()) {
                System.out.println("No departments found.");
            } else {
                System.out.println(String.format("%-5s %-25s %-30s %-15s %-10s", 
                    "ID", "NAME", "LOCATION", "STATUS", "BUDGET"));
                System.out.println("=".repeat(85));
                for (Department dept : departments) {
                    System.out.println(String.format("%-5d %-25s %-30s %-15s %-10.0f", 
                        dept.getId(), 
                        dept.getName(), 
                        dept.getLocation(), 
                        dept.getStatus(), 
                        dept.getBudget()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
