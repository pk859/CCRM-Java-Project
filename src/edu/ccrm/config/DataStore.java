package edu.ccrm.config;

import java.util.ArrayList;
import java.util.List;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

// The Singleton pattern ensures only one instance of DataStore exists.
public class DataStore {
    // 1. A private static final instance of the class itself.
    private static final DataStore instance = new DataStore();

    // The actual data lists for the application
    public final List<Student> students = new ArrayList<>();
    public final List<Course> courses = new ArrayList<>();

    // 2. A private constructor prevents anyone else from creating an instance.
    private DataStore() {}

    // 3. A public static method to get the single instance.
    public static DataStore getInstance() {
        return instance;
    }
}