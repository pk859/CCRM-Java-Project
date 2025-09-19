package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;

public class GradingService {
    public boolean assignGrade(Student student, Course course, Grade grade) {
        // Find the specific enrollment for this student and course
        for (Enrollment enrollment : student.getEnrollments()) {
            if (enrollment.getCourse().equals(course)) {
                // Assign the grade to that enrollment
                enrollment.setGrade(grade);
                return true; // Indicate success
            }
        }
        return false; // Indicate failure (student was not enrolled in the course)
    }
}