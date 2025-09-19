package edu.ccrm.cli;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.io.BackupService; // New import
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.GradingService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.TranscriptService;
import java.io.IOException; // New import
import java.nio.file.Paths; // New import
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final GradingService gradingService = new GradingService();
    private static final TranscriptService transcriptService = new TranscriptService();
    private static final ImportExportService importExportService = new ImportExportService();
    private static final BackupService backupService = new BackupService(); // New Service
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        importExportService.importData(DataStore.getInstance());
        boolean exit = false;
        while (!exit) {
            printMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: manageStudents(); break;
                    case 2: manageCourses(); break;
                    case 3: manageEnrollment(); break;
                    case 4: recordGrades(); break;
                    case 5: printTranscript(); break;
                    case 6: // New Option for Backup
                        try {
                            backupService.performBackup();
                        } catch (IOException e) {
                            System.out.println("Error creating backup: " + e.getMessage());
                        }
                        break;
                    case 7: // New Option for Recursive Size
                        try {
                            long size = backupService.calculateDirectorySize(Paths.get("backups"));
                            System.out.println("Total size of backups directory: " + size + " bytes.");
                        } catch (IOException e) {
                            System.out.println("Error calculating backup size: " + e.getMessage());
                        }
                        break;
                    case 9: exit = true; break;
                    default: System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        importExportService.exportData(DataStore.getInstance());
        System.out.println("Exiting CCRM. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n--- Campus Course & Records Manager ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment");
        System.out.println("4. Record Grades");
        System.out.println("5. Print Student Transcript");
        System.out.println("6. Backup Data"); // New Option
        System.out.println("7. Show Total Backup Size (Recursive)"); // New Option
        System.out.println("9. Exit");
    }

    // All the manage...() and print...() methods below remain the same.
    // I have included them here so you have the complete, final file.

    private static void manageStudents() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID (e.g., 101): ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Registration Number (e.g., S001): ");
                        String regNo = scanner.nextLine();
                        studentService.addStudent(new Student(id, name, email, regNo));
                        break;
                    case 2:
                        List<Student> allStudents = studentService.getAllStudents();
                        if (allStudents.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("\n--- List of All Students ---");
                            allStudents.forEach(student -> System.out.println(student.getDetails()));
                        }
                        break;
                    case 9: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private static void manageCourses() {
         boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add New Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Search Courses by Semester");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Course Code (e.g., CS101): ");
                        String code = scanner.nextLine();
                        System.out.print("Enter Course Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Credits: ");
                        int credits = Integer.parseInt(scanner.nextLine());
                        courseService.addCourse(new Course.CourseBuilder(code, title).credits(credits).build());
                        break;
                    case 2:
                        List<Course> allCourses = courseService.getAllCourses();
                        if (allCourses.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            System.out.println("\n--- List of All Courses ---");
                            allCourses.forEach(System.out::println);
                        }
                        break;
                    case 3:
                        System.out.print("Enter Semester to search (SPRING, SUMMER, or FALL): ");
                        String semesterStr = scanner.nextLine().toUpperCase();
                        try {
                            Semester semester = Semester.valueOf(semesterStr);
                            List<Course> foundCourses = courseService.findCoursesBySemester(semester);
                            if (foundCourses.isEmpty()) {
                                System.out.println("No courses found for " + semester);
                            } else {
                                System.out.println("\n--- Courses in " + semester + " ---");
                                foundCourses.forEach(System.out::println);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid semester.");
                        }
                        break;
                    case 9: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void manageEnrollment() {
        System.out.println("\n--- Enroll Student in Course ---");
        try {
            System.out.print("Enter Student ID to enroll: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            Student student = studentService.findStudentById(studentId);
            if (student == null) {
                System.out.println("Error: Student with ID " + studentId + " not found.");
                return;
            }
            System.out.print("Enter Course Code to enroll in: ");
            String courseCode = scanner.nextLine();
            Course course = courseService.findCourseByCode(courseCode);
            if (course == null) {
                System.out.println("Error: Course with code " + courseCode + " not found.");
                return;
            }
            enrollmentService.enroll(student, course);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        } catch (DuplicateEnrollmentException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }
    }
    
    private static void recordGrades() {
        System.out.println("\n--- Record Student Grade ---");
        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            Student student = studentService.findStudentById(studentId);
            if (student == null) {
                System.out.println("Error: Student not found.");
                return;
            }
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = courseService.findCourseByCode(courseCode);
            if (course == null) {
                System.out.println("Error: Course not found.");
                return;
            }
            System.out.print("Enter Grade (S, A, B, C, D, E, or F): ");
            String gradeStr = scanner.nextLine().toUpperCase();
            Grade grade = Grade.valueOf(gradeStr);
            if (gradingService.assignGrade(student, course, grade)) {
                System.out.println("Grade recorded successfully.");
            } else {
                System.out.println("Error: Could not record grade. Student may not be enrolled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid grade entered.");
        }
    }

    private static void printTranscript() {
        System.out.println("\n--- Print Student Transcript ---");
        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            Student student = studentService.findStudentById(studentId);
            if (student == null) {
                System.out.println("Error: Student not found.");
                return;
            }
            transcriptService.printTranscript(student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }
}