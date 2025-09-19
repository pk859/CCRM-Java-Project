package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    // This is the updated field
    private List<Enrollment> enrollments;
    private LocalDate registrationDate;

    public Student(int id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollments = new ArrayList<>(); // Changed to Enrollment
        this.registrationDate = LocalDate.now();
    }

    public int getId() {
        return this.id;
    }

    // This method is now updated
    public List<Enrollment> getEnrollments() {
        return this.enrollments;
    }

    @Override
    public String getDetails() {
        return "Student: " + getFullName() + " (Reg No: " + this.regNo + ")";
    }

    @Override
    public String toString() {
        return getDetails();
    }
    
    public String getRegNo() {
        return regNo;
    }
}