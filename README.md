# Campus Course & Records Manager (CCRM)

## Project Overview
The CCRM is a console-based Java application designed to manage students, courses, enrollments, and grades for an educational institute. It features data persistence through file I/O, a backup utility, and demonstrates core Java principles, Object-Oriented Programming, and modern Java APIs.

---

## How to Run
1.  **Prerequisites:** Java JDK 17 or newer.
2.  **Clone the repository:**
    ```bash
    git clone [https://github.com/pk859/CCRM-Java-Project.git](https://github.com/pk859/CCRM-Java-Project.git)
    ```
3.  **Navigate to the project directory and run:**
    * Open the `ccrm` project folder in VS Code.
    * Ensure the "Extension Pack for Java" is installed.
    * Open the `src/edu/ccrm/cli/Main.java` file.
    * Click the "▶ Run" button above the `main` method.

---

## Core Java Concepts

### Evolution of Java
* **JDK 1.0 (1996):** The initial release.
* **Java 5 (2004):** Introduced major features like Generics, Enums, Annotations, and the `for-each` loop.
* **Java 8 (2014):** A landmark release that added Lambda Expressions, the Stream API, and a new Date/Time API.
* **Java 11 (2018):** The first Long-Term Support (LTS) release after Java 8.
* **Java 17 (2021):** The next LTS release, bringing features like Sealed Classes and Pattern Matching for `instanceof`.

### Java ME vs. SE vs. EE
* **Java SE (Standard Edition):** The core platform for developing desktop and server applications. This project is built using Java SE.
* **Java EE (Enterprise Edition):** Built on top of SE, it provides an API and runtime environment for developing large-scale, multi-tiered, and secure network applications.
* **Java ME (Micro Edition):** A subset of SE for developing applications for small, resource-constrained devices like mobile phones and embedded systems.

### Java Architecture: JDK, JRE, JVM
* **JVM (Java Virtual Machine):** An abstract machine that executes Java bytecode. It's the component that makes Java "write once, run anywhere."
* **JRE (Java Runtime Environment):** The software package that provides the JVM and the Java class libraries necessary to *run* Java applications.
* **JDK (Java Development Kit):** The full development kit for Java programmers. It includes the JRE, a compiler (`javac`), a debugger, and other development tools.

---

## Setup and Installation

### Verifying Java Installation on Windows
*(Embed your `java -version` screenshot here)*

### Visual Studio Code Setup Steps
*Note: This project was developed using Visual Studio Code, a modern alternative to Eclipse for Java development. The setup process is documented below.*

1.  Install the "Extension Pack for Java" from Microsoft in VS Code.
2.  Open the project folder (`ccrm`).
3.  VS Code will automatically recognize it as a Java project.

*(Embed your VS Code project structure screenshot here)*

---

## Features Mapping Table
| Syllabus Topic | File / Class / Method Where Demonstrated |
| :--- | :--- |
| **OOP Pillars** | |
| Encapsulation | `Person.java`, `Student.java` (protected fields, public getters) |
| Inheritance | `Student.java` and `Instructor.java` extend `Person.java` |
| Abstraction | `Person.java` (abstract class with an abstract `getDetails()` method) |
| Polymorphism | `main` method calling service methods that use `Person` subtypes. |
| **Design Patterns** | |
| Singleton | `DataStore.java` |
| Builder | `Course.java` (inner `CourseBuilder` class) |
| **Core Java** | |
| Custom Exceptions | `DuplicateEnrollmentException.java`, used in `EnrollmentService.java` |
| Streams API | `CourseService.java` (searching), `ImportExportService.java` (file processing) |
| NIO.2 File I/O | `ImportExportService.java`, `BackupService.java` (`Path`, `Files`) |
| Date/Time API | `BackupService.java` (for timestamped folders) |
| Recursion | `BackupService.java` (`calculateDirectorySize` method using `Files.walk`) |
| Enums w/ Fields | `Grade.java` (with grade points) |
| Lambdas | `CourseService.java` (in `.filter()` method), `BackupService.java` |

---

## Notes on Assertions
Assertions are used for internal checks and must be enabled at runtime. An example could be added to `StudentService.java`:
```java
// In StudentService.java
public void addStudent(Student student) {
    assert student.getId() > 0 : "Student ID must be positive";
    // ... rest of the method
}
```
To run the program with assertions enabled in VS Code, you would modify the `.vscode/launch.json` file to include a `vmArgs` entry: `"vmArgs": "-ea"`.