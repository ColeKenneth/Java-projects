package studentManagement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final StudentStorage student = new StudentStorage();

    public static void main(String[] args) {
        int choice;

        do {
            try {
                System.out.println("-----STUDENT MANAGEMENT SYSTEM-----");
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

    public static void addStudent() {
        Student newStudent = null;
        int studentID = getNumber("Student ID: ");
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


                try {
                    System.out.print("Entrance Exam Score: ");
                    entranceExamScore = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Your score must be a number.");
                    entranceExamScore = 0.0;
                }


                newStudent = new Freshman(studentID, firstName, middleName, lastName, age, yearLevel, isOrientationComplete, highSchoolOrigin, entranceExamScore);
            }
            case SOPHOMORE -> {
                System.out.print("Declared Major: ");
                declaredMajor = sc.nextLine();

                generalEducationCredits = getNumber("General Education Credits: ");

                System.out.print("Eligible for Minor? (true/false): ");
                isEligibleForMinor = Boolean.parseBoolean(sc.nextLine());

                newStudent = new Sophomore(studentID, firstName, middleName, lastName, age, yearLevel, declaredMajor, generalEducationCredits, isEligibleForMinor);
            }
            case JUNIOR -> {
                internshipHours = getNumber("Internship Hours: ");

                System.out.print("Specialization: ");
                specialization = sc.nextLine().trim();

                System.out.println("Enter your electives (type 'done' to finish):");
                while (true) {
                    System.out.print("- ");
                    String elective = sc.nextLine().trim();

                    if (elective.equalsIgnoreCase("done")) {
                        break;
                    }

                    if (!elective.isEmpty()) {
                        electives.add(elective);
                    }
                }
                newStudent = new Junior(studentID, firstName, middleName, lastName, age, yearLevel, internshipHours, specialization, electives);

            }
            case SENIOR -> {
                System.out.print("Thesis Title: ");
                thesisTitle = sc.nextLine().trim();
                try {
                    System.out.print("Expected Graduation Date (Format: MM-DD-YYYY): ");
                    String graduationDate = sc.nextLine().trim();
                    expectedGraduationDate = LocalDate.parse(graduationDate, format);
                } catch (DateTimeException e) {
                    System.err.println("Invalid date format!");
                    expectedGraduationDate = LocalDate.now();
                }

                System.out.print("Is Career Ready? (true/false): ");
                isCareerReady = Boolean.parseBoolean(sc.nextLine());

                newStudent = new Senior(studentID, firstName, middleName, lastName, age, yearLevel, thesisTitle, expectedGraduationDate, isCareerReady);
            }

        }
        if (newStudent != null) {
            student.addStudent(newStudent);
        }

    }

    public static void viewStudents() {
        student.listStudents();
    }

    public static void deleteMenu() {
        int studentID = getNumber("Enter student ID: ");
        student.deleteStudent(studentID);
    }

    public static void updateMenu() {
        try {
            System.out.println("---UPDATE MENU---");
            System.out.println("1. Update Name \n2. Update Age \n3. Update Year Level");
            int choice = getNumber("Select a number to update the following: ");

            switch (choice) {
                case 1 -> {
                    System.out.println("---UPDATE STUDENT'S NAME---");
                    int id = getNumber("Student ID: ");
                    System.out.print("First Name: ");
                    String firstName = sc.nextLine().trim();

                    System.out.print("Last Name: ");
                    String lastName = sc.nextLine().trim();

                    student.updateStudentName(id, firstName, lastName);
                }
                case 2 -> {
                    System.out.println("---UPDATE AGE---");
                    int id = getNumber("Student ID: ");
                    int age = getNumber("Age: ");
                    student.updateStudentAge(id, age);
                }
                case 3 -> {
                    System.out.println("---UPDATE YEAR LEVEL---");
                    int id = getNumber("Student ID: ");
                    YearLevel yearLevel = getYearLevel();

                    student.updateYearLevel(id,yearLevel);
                }
                default -> System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}

