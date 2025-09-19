package edu.ccrm.service;

import java.util.List;
import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;

public class StudentService {
    // Get the single instance of our DataStore
    private final DataStore dataStore = DataStore.getInstance();

    public void addStudent(Student student) {
        // TODO: Add logic to check for duplicate registration numbers
        this.dataStore.students.add(student);
        System.out.println("Student added successfully: " + student.getFullName());
    }

    public List<Student> getAllStudents() {
        return this.dataStore.students;
    }

    // --- NEW FINDER METHOD ---
    // Finds a student by their unique integer ID.
    public Student findStudentById(int id) {
        for (Student student : this.dataStore.students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // Return null if not found
    }
}