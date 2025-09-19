package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;

public class TranscriptService {

    public void printTranscript(Student student) {
        System.out.println("\n--- Academic Transcript ---");
        System.out.println("Student: " + student.getFullName() + " (ID: " + student.getId() + ")");
        System.out.println("---------------------------------");

        if (student.getEnrollments().isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        for (Enrollment enrollment : student.getEnrollments()) {
            System.out.println(enrollment); // Uses the toString() override from Enrollment
        }

        double gpa = calculateGPA(student);
        // Print GPA formatted to two decimal places
        System.out.printf("Cumulative GPA: %.2f\n", gpa);
        System.out.println("---------------------------------");
    }

    private double calculateGPA(Student student) {
        if (student.getEnrollments().isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment enrollment : student.getEnrollments()) {
            if (enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                totalPoints += enrollment.getGrade().getGradePoint() * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }
        return totalPoints / totalCredits;
    }
}