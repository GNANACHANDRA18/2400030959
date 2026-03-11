package com.klef.fsad.exam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Department Entity Class
 * This class represents the Department table in the database
 */
@Entity
@Table(name = "departments")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "dept_date")
    @Temporal(TemporalType.DATE)
    private Date deptDate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "budget")
    private double budget;

    // Constructors
    
    /**
     * Default Constructor
     */
    public Department() {
    }

    /**
     * Constructor with parameters
     */
    public Department(String name, String description, Date deptDate, String status, String location, double budget) {
        this.name = name;
        this.description = description;
        this.deptDate = deptDate;
        this.status = status;
        this.location = location;
        this.budget = budget;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(Date deptDate) {
        this.deptDate = deptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    // toString method
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deptDate=" + deptDate +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", budget=" + budget +
                '}';
    }
}
