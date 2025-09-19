package edu.ccrm.exception;

// A custom 'checked' exception
public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}