package edu.ccrm.domain;

// Instructor also inherits from Person
public class Instructor extends Person {
    private String department;

    public Instructor(int id, String fullName, String email, String department) {
        // Call the parent Person constructor
        super(id, fullName, email);
        this.department = department;
    }

    // Provide the required implementation for the abstract method
    @Override
    public String getDetails() {
        return "Instructor: " + getFullName() + " (Dept: " + this.department + ")";
    }

    @Override
    public String toString() {
        return getDetails();
    }
}