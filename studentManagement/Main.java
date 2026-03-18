package studentManagement;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static void main(String[] args) {
        int choice = 0;

        do {
            try {
                System.out.println("\n-----STUDENT MANAGEMENT SYSTEM-----");
                System.out.println("1. Add Student \n2. View Students \n3. Delete Students \n4. Update Students \n5. Exit");
                System.out.print("Select what you want to do: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> deleteMenu();
                    case 4 -> updateMenu();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                choice = 0;
            } catch (StudentNotFoundException | StudentAlreadyExistsException | GraduationOutOfRangeException e) {
                System.err.println("DATA ERROR: " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Something went wrong. Please try again.");
            }
        } while (choice != 5);
    }

    private static int getNumber(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String inputValue = sc.nextLine().trim();
                return Integer.parseInt(inputValue);
            } catch (NumberFormatException e) {
                System.err.println("Please input a number.");
            }
        }
    }

    private static YearLevel getYearLevel() {
        while (true) {
            try {
                System.out.println("Year Level: \n1. Freshman \n2. Sophomore \n3. Junior \n4. Senior");
                int choice = Integer.parseInt(sc.nextLine());

                switch(choice) {
                    case 1 -> {
                        return YearLevel.FRESHMAN;
                    }
                    case 2 -> {
                        return YearLevel.SOPHOMORE;
                    }
                    case 3 -> {
                        return YearLevel.JUNIOR;
                    }
                    case 4 -> {
                        return YearLevel.SENIOR;
                    }
                    default -> System.out.println("Invalid year level!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Your year level is only selected via 1-4.");
            }

        }
    }

    private static void processUpdateResult(boolean success) {
        if (success) {
            System.out.println("Data updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public static void addStudent() {
        try {
            Student newStudent = null;
            System.out.print("First Name: ");
            String firstName = sc.nextLine().trim();

            System.out.print("Middle Name: ");
            String middleName = sc.nextLine().trim();

            System.out.print("Last Name: ");
            String lastName = sc.nextLine().trim();

            int age = getNumber("Age: ");
            YearLevel yearLevel = getYearLevel();


            boolean isOrientationComplete = false;
            String highSchoolOrigin = "N/A";
            double entranceExamScore = 0;
            String declaredMajor = "N/A";
            int generalEducationCredits = 0;
            boolean isEligibleForMinor = false;
            int internshipHours = 0;
            String specialization = "N/A";
            List<String> electives = new ArrayList<>();
            String thesisTitle = "N/A";
            LocalDate expectedGraduationDate = null;
            boolean isCareerReady = false;

            switch (yearLevel) {
                case FRESHMAN -> {
                    System.out.print("Is Orientation Complete? (true/false): ");
                    isOrientationComplete = Boolean.parseBoolean(sc.nextLine());

                    System.out.print("Last School Attended (High School): ");
                    highSchoolOrigin = sc.nextLine().trim();

                    while (true) {
                        try {
                            System.out.print("Entrance Exam Score: ");
                            entranceExamScore = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.err.println("Your score must be a number.");
                        }
                    }
                    newStudent = new Freshman(0, firstName, middleName, lastName, age,
                            yearLevel, isOrientationComplete, highSchoolOrigin, entranceExamScore);
                }
                case SOPHOMORE -> {
                    System.out.print("Declared Major: ");
                    declaredMajor = sc.nextLine();
                    generalEducationCredits = getNumber("General Education Credits: ");
                    System.out.print("Eligible for Minor? (true/false): ");
                    isEligibleForMinor = Boolean.parseBoolean(sc.nextLine());

                    newStudent = new Sophomore(0, firstName, middleName, lastName, age, yearLevel, declaredMajor, generalEducationCredits, isEligibleForMinor);
                }
                case JUNIOR -> {
                    internshipHours = getNumber("Internship Hours: ");
                    System.out.print("Specialization: ");
                    specialization = sc.nextLine().trim();

                    System.out.println("Enter your electives (type 'done' to finish):");
                    while (true) {
                        System.out.print("- ");
                        String elective = sc.nextLine().trim();
                        if (elective.equalsIgnoreCase("done")) break;
                        if (!elective.isEmpty()) electives.add(elective);
                    }
                    newStudent = new Junior(0, firstName, middleName, lastName, age, yearLevel, internshipHours, specialization, electives);
                }
                case SENIOR -> {
                    System.out.print("Thesis Title: ");
                    thesisTitle = sc.nextLine().trim();
                    try {
                        System.out.print("Expected Graduation Date (Format: MM-DD-YYYY): ");
                        String graduationDate = sc.nextLine().trim();
                        expectedGraduationDate = LocalDate.parse(graduationDate, format);
                    } catch (DateTimeException e) {
                        System.err.println("Invalid date format! Setting to today.");
                        expectedGraduationDate = LocalDate.now();
                    }
                    System.out.print("Is Career Ready? (true/false): ");
                    isCareerReady = Boolean.parseBoolean(sc.nextLine());

                    newStudent = new Senior(0, firstName, middleName, lastName, age, yearLevel, thesisTitle, expectedGraduationDate, isCareerReady);
                }
            }


            if (newStudent != null) {
                StudentStorage.addStudent(newStudent);
                System.out.println("\nSUCCESS: " + firstName + " has been registered.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("\nDATA ERROR: " + e.getMessage());
            System.out.println("Returning to main menu...");
        } catch (SQLException e) {
            System.err.println("\nDATABASE ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nUNEXPECTED ERROR: " + e.getMessage());
        }
    }

    public static void viewStudents() {
        List<Student> students = StudentStorage.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the records.");
        } else {
            System.out.println("\nENROLLED STUDENTS");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public static void deleteMenu() {
        try {
            int studentID = getNumber("Enter student ID: ");
            StudentStorage.deleteStudent(studentID);
        } catch (StudentNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("DATABASE ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("SYSTEM ERROR: " + e.getMessage());
        }
    }

    public static void updateMenu() {
        System.out.println("""
                UPDATE MENU
                1. Update Student Base
                2. Update Age
                3. Update Freshman
                4. Update Sophomore
                5. Update Junior
                6. Update Electives
                7. Update Senior
                """);
        int choice = getNumber("Select from the menu to update: ");

        switch (choice) {
            case 1 -> {
                System.out.println("UPDATE STUDENT BASE");
                int studentID = getNumber("Enter Student ID to update: ");

                if (!StudentStorage.exists(studentID)) {
                    System.out.println("Student not found!");
                    return;
                }
                System.out.print("First Name: ");
                String firstName = sc.nextLine().trim();

                System.out.print("Middle Name: ");
                String middleName = sc.nextLine().trim();

                System.out.print("Last Name: ");
                String lastName = sc.nextLine().trim();

                try {
                    boolean success = StudentStorage.updateStudentName(studentID, firstName, middleName, lastName);
                   processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            case 2 -> {
                System.out.println("UPDATE STUDENT'S AGE");
                int studentID = getNumber("Enter Student ID to update: ");
                int age = getNumber("Age: ");

                try {
                    boolean success = StudentStorage.updateStudentAge(studentID, age);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            case 3 -> {
                System.out.println("UPDATE FRESHMAN");
                int studentID = getNumber("Enter Student ID to update: ");
                System.out.println("Is Orientation Complete? (true/false): ");
                boolean orientationComplete = Boolean.parseBoolean(sc.nextLine());

                System.out.print("High School Origin: ");
                String highSchoolOrigin = sc.nextLine().trim();

                double entranceExamScore = 0.0;
                while (true) {
                    try {
                        System.out.print("Entrance Exam Score: ");
                        entranceExamScore = Double.parseDouble(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("ERROR: " + e.getMessage());

                    }
                }

                try {
                    boolean success = StudentStorage.updateFreshman(studentID, orientationComplete, highSchoolOrigin, entranceExamScore);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            case 4 -> {
                System.out.println("UPDATE SOPHOMORE");
                int studentID = getNumber("Enter student ID to update: ");
                System.out.print("Declared Major: ");
                String declaredMajor = sc.nextLine().trim();

                int generalEducationCredits = getNumber("General Education Credits: ");

                System.out.print("Eligible for Minor? (true/false): ");
                boolean eligibleForMinor = Boolean.parseBoolean(sc.nextLine());

                try {
                    boolean success = StudentStorage.updateSophomore(studentID, declaredMajor, generalEducationCredits, eligibleForMinor);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            case 5 -> {
                System.out.println("UPDATE JUNIOR");
                int studentID = getNumber("Enter student ID to update: ");
                int internshipHours = getNumber("Internship Hours: ");
                System.out.print("Specialization: ");
                String specialization = sc.nextLine().trim();

                try {
                    boolean success = StudentStorage.updateJunior(studentID, internshipHours, specialization);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }

            }
            case 6 -> {
                System.out.println("UPDATE ELECTIVES");
                int studentID = getNumber("Enter student ID to update: ");

                List<String> updatedElectives = new ArrayList<>();
                String input;

                System.out.println("Enter your updated electives (type 'done' to finish).");
                while (true) {
                    System.out.print("> ");
                    input = sc.nextLine().trim();

                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }

                    if (!input.isEmpty()) {
                        updatedElectives.add(input);
                    }
                }

                try {
                    boolean success = StudentStorage.updateElectives(studentID, updatedElectives);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            case 7 -> {
                System.out.println("UPDATE SENIOR");
                int studentID = getNumber("Enter student ID to update: ");

                System.out.print("Thesis Title: ");
                String thesisTitle = sc.nextLine().trim();

                System.out.print("Expected Date of Graduation (FORMAT: MM-dd-yyyy): ");
                String updatedDate = sc.nextLine().trim();

                System.out.print("Is Career Ready? (true/false): ");
                boolean careerReady = Boolean.parseBoolean(sc.nextLine());

                try {
                    LocalDate expectedGraduationDate = LocalDate.parse(updatedDate, format);
                    boolean success = StudentStorage.updateSenior(studentID, thesisTitle, expectedGraduationDate, careerReady);
                    processUpdateResult(success);
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            }
            default -> System.out.println("Invalid choice!");
        }
    }
}

