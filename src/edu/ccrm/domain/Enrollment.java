package edu.ccrm.domain;

public class Enrollment {
    private Course course;
    private Grade grade; // Grade can be null if not yet assigned

    public Enrollment(Course course) {
        this.course = course;
        this.grade = null; // Default to no grade
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String gradeStr = (grade == null) ? "Not Graded" : grade.toString();
        return "Course: " + course.getTitle() + ", Grade: " + gradeStr;
    }
}