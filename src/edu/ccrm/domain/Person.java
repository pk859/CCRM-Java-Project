package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String fullName;
    protected String email;
    protected LocalDate dateOfBirth;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public abstract String getDetails();

    public String getFullName() {
        return fullName;
    }

    // --- NEW GETTER METHOD ---
    public String getEmail() {
        return email;
    }
}