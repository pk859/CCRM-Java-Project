package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateEnrollmentException;

public class EnrollmentService {
    
    public void enroll(Student student, Course course) throws DuplicateEnrollmentException {
        // Business Rule: Check if the student is already enrolled in this course
        for (Enrollment existingEnrollment : student.getEnrollments()) {
            if (existingEnrollment.getCourse().equals(course)) {
                throw new DuplicateEnrollmentException(
                    student.getFullName() + " is already enrolled in " + course.getTitle()
                );
            }
        }

        // If not, create a new Enrollment object and add it to the student's list
        Enrollment newEnrollment = new Enrollment(course);
        student.getEnrollments().add(newEnrollment);
        System.out.println("Enrollment successful!");
    }
}