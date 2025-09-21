# Campus Course & Records Manager (CCRM)

## Project Overview
[cite_start]The CCRM is a console-based Java application designed to manage students, courses, enrollments, and grades for an educational institute[cite: 4]. [cite_start]It features data persistence through file I/O, a backup utility, and a command-line interface for all operations[cite: 10]. [cite_start]This project demonstrates core Java principles, Object-Oriented Programming, and modern Java APIs like Streams, NIO.2, and the Date/Time API[cite: 12].

---

## How to Run
* **JDK Version:** Java 17 or newer is required.
* **Clone the repository:**
    ```bash
    git clone [https://github.com/pk859/CCRM-Java-Project.git](https://github.com/pk859/CCRM-Java-Project.git)
    ```
* **Run from VS Code:**
    1.  Open the cloned `ccrm` project folder in Visual Studio Code.
    2.  Ensure the "Extension Pack for Java" from Microsoft is installed.
    3.  Open the `src/edu/ccrm/cli/Main.java` file.
    4.  Click the "▶ Run" button that appears above the `main` method.

---

## Core Java Concepts

### Evolution of Java
* **JDK 1.0 (1996):** The initial release of Java.
* **Java 5 (2004):** A major update that introduced Generics, Enums, Annotations, and the `for-each` loop.
* [cite_start]**Java 8 (2014):** A landmark release that added Lambda Expressions, the Stream API, and a new Date/Time API[cite: 12].
* **Java 11 (2018):** The first Long-Term Support (LTS) release after Java 8, standardizing many features.
* **Java 17 (2021):** The next LTS release, bringing features like Sealed Classes and improved Pattern Matching.

### [cite_start]Java ME vs SE vs EE [cite: 43]
* **Java SE (Standard Edition):** The core platform for developing desktop and server applications. [cite_start]This project is built using Java SE[cite: 11, 43].
* [cite_start]**Java EE (Enterprise Edition):** Built on top of SE, it provides an API and runtime environment for developing large-scale, multi-tiered, and secure network applications[cite: 43].
* [cite_start]**Java ME (Micro Edition):** A subset of SE for developing applications for small, resource-constrained devices like mobile phones and embedded systems[cite: 43].

### [cite_start]Java Architecture: JDK, JRE, JVM [cite: 44]
* **JVM (Java Virtual Machine):** An abstract machine that executes Java bytecode. [cite_start]It's the component that makes Java "write once, run anywhere." [cite: 44]
* [cite_start]**JRE (Java Runtime Environment):** The software package that provides the JVM and the Java class libraries necessary to *run* Java applications[cite: 44].
* **JDK (Java Development Kit):** The full development kit for Java programmers. [cite_start]It includes the JRE, a compiler (`javac`), a debugger, and other development tools needed to *develop* applications[cite: 44].

---

## Setup and Installation

### [cite_start]Windows Install Steps [cite: 45]
Java JDK was installed on Windows, and its version was verified via the command line.

*(Embed your `java -version` screenshot here)*

### Visual Studio Code Setup Steps
[cite_start]*Note: The project requirements specified Eclipse, but this project was developed using Visual Studio Code, a modern and widely-used alternative for Java development.* [cite: 46]

1.  The "Extension Pack for Java" from Microsoft was installed in VS Code.
2.  The project folder was opened, and VS Code automatically configured it as a Java project.

*(Embed your VS Code project structure screenshot here)*

---

## Features Mapping Table
| Syllabus Topic | File / Class / Method Where Demonstrated |
| :--- | :--- |
| **OOP Pillars** | |
| [cite_start]Encapsulation [cite: 59] | `Person.java`, `Student.java` (protected fields, public getters) |
| [cite_start]Inheritance [cite: 60, 64] | `Student.java` and `Instructor.java` extend `Person.java` |
| [cite_start]Abstraction [cite: 61] | `Person.java` (abstract class with an abstract `getDetails()` method) |
| [cite_start]Polymorphism [cite: 62] | `TranscriptService.java` printing `Enrollment` objects, which polymorphically display `Course` details. |
| [cite_start]**Design Patterns** [cite: 12, 78]| |
| [cite_start]Singleton [cite: 80] | `DataStore.java` |
| [cite_start]Builder [cite: 81] | `Course.java` (inner `CourseBuilder` static nested class) |
| **Core Java** | |
| [cite_start]Custom Exceptions [cite: 86] | `DuplicateEnrollmentException.java`, thrown in `EnrollmentService.java` |
| [cite_start]Streams API [cite: 12, 23, 92] | `CourseService.java` (searching), `ImportExportService.java` (file processing) |
| [cite_start]NIO.2 File I/O [cite: 12, 90, 91] | `ImportExportService.java`, `BackupService.java` (`Path`, `Files`) |
| [cite_start]Date/Time API [cite: 12, 18, 94] | `BackupService.java` (for timestamped folders), `Student.java` (registration date) |
| [cite_start]Recursion [cite: 12, 33] | `BackupService.java` (`calculateDirectorySize` method using `Files.walk`) |
| [cite_start]Enums w/ Fields [cite: 12, 27, 74] | `Grade.java` (with grade points) |
| [cite_start]Lambdas [cite: 12, 72] | `CourseService.java` (in `.filter()` method), `BackupService.java` |
| [cite_start]Packages [cite: 12, 49] | `edu.ccrm.cli`, `edu.ccrm.domain`, `edu.ccrm.service`, etc. |

---

## [cite_start]Notes on Enabling Assertions [cite: 89]
Assertions are used for internal checks to verify assumptions about the program's state. They must be enabled at runtime.

An example could be added to `StudentService.java`:
```java
// In StudentService.java
public void addStudent(Student student) {
    assert student.getId() > 0 : "Student ID must be positive";
    // ... rest of the method
}
```
To run the program with assertions enabled in VS Code, you would modify the `.vscode/launch.json` file to include a `vmArgs` entry: `"vmArgs": "-ea"`.

### Sample Commands
* In the main menu, press `1` to enter Student Management.
* In the student sub-menu, press `1` to add a new student.
* Follow the prompts to enter student details.
* Press `2` to list all students and see the new entry.
* Press `9` to go back to the main menu.