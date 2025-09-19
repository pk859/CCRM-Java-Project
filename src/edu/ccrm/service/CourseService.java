package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private final DataStore dataStore = DataStore.getInstance();

    public void addCourse(Course course) {
        this.dataStore.courses.add(course);
        System.out.println("Course added successfully: " + course.getTitle());
    }

    public List<Course> findCoursesBySemester(Semester semester) {
        return this.dataStore.courses.stream()
                .filter(course -> course.getSemester() == semester)
                .collect(Collectors.toList());
    }

    public List<Course> getAllCourses() {
        return this.dataStore.courses;
    }

    // --- NEW FINDER METHOD ---
    // Finds a course by its unique course code (case-insensitive).
    public Course findCourseByCode(String code) {
        return this.dataStore.courses.stream()
            .filter(course -> course.getCode().equalsIgnoreCase(code))
            .findFirst() // Find the first match
            .orElse(null); // Return null if not found
    }
}