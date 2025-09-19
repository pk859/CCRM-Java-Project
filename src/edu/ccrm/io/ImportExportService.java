package edu.ccrm.io;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportExportService {
    private static final Path STUDENTS_FILE = Paths.get("data/students.csv");
    private static final Path COURSES_FILE = Paths.get("data/courses.csv");

    public void exportData(DataStore dataStore) {
        System.out.println("Saving data to files...");
        try {
            // Ensure the 'data' directory exists
            Files.createDirectories(STUDENTS_FILE.getParent());

            // --- Use Streams to convert objects to CSV lines ---
            List<String> studentLines = dataStore.students.stream()
                .map(s -> s.getId() + "," + s.getFullName() + "," + s.getEmail() + "," + s.getRegNo())
                .collect(Collectors.toList());
            Files.write(STUDENTS_FILE, studentLines);

            List<String> courseLines = dataStore.courses.stream()
                .map(c -> c.getCode() + "," + c.getTitle() + "," + c.getCredits())
                .collect(Collectors.toList());
            Files.write(COURSES_FILE, courseLines);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void importData(DataStore dataStore) {
        System.out.println("Loading data from files...");
        // Import students
        if (Files.exists(STUDENTS_FILE)) {
            try (Stream<String> stream = Files.lines(STUDENTS_FILE)) {
                stream.forEach(line -> {
                    String[] parts = line.split(",");
                    Student student = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
                    dataStore.students.add(student);
                });
            } catch (IOException e) {
                System.out.println("Error loading students: " + e.getMessage());
            }
        }

        // Import courses
        if (Files.exists(COURSES_FILE)) {
            try (Stream<String> stream = Files.lines(COURSES_FILE)) {
                stream.forEach(line -> {
                    String[] parts = line.split(",");
                    Course course = new Course.CourseBuilder(parts[0], parts[1])
                        .credits(Integer.parseInt(parts[2]))
                        .build();
                    dataStore.courses.add(course);
                });
            } catch (IOException e) {
                System.out.println("Error loading courses: " + e.getMessage());
            }
        }
        System.out.println("Data loading complete.");
    }
}