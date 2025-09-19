package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final Semester semester;
    private Instructor instructor; // An instructor can be assigned to a course

    // The constructor is private, so you MUST use the Builder to create a Course object.
    private Course(CourseBuilder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.semester = builder.semester;
        this.instructor = builder.instructor;
    }

    // Standard getters for the fields
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }
    
    public Semester getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Course [Code=" + code + ", Title=" + title + "]";
    }

    // --- The Builder Pattern ---
    // This is a static nested class.
    public static class CourseBuilder {
        private final String code;
        private final String title;
        private int credits; // optional
        private Semester semester; // optional
        private Instructor instructor; // optional

        // The builder constructor takes the mandatory fields.
        public CourseBuilder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        // Each method for an optional field returns the builder itself.
        public CourseBuilder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public CourseBuilder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public CourseBuilder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        // The build() method calls the private constructor to create the final object.
        public Course build() {
            return new Course(this);
        }
    }
}