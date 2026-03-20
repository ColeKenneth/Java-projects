package misc;

import java.util.*;


public class ListIteratorDemo {
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> students = new ArrayList<>();
    public static void main(String[] args) {
        int choice = 0;
        do {
            try {
                System.out.println("\nMenu");
                System.out.println("1. Add Student \n2. Remove Student \n3. View Students \n4. Exit");
                System.out.print("Choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> removeStudent();
                    case 3 -> viewStudents();
                    case 4 -> searchStudent();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 5);
    }

    public static void addStudent() {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine().trim();

        boolean exists = students.stream()
                        .anyMatch(s -> s.equalsIgnoreCase(name));

        if (exists) {
            throw new IllegalArgumentException(name + " already in the list!");
        }

        students.add(name);
        System.out.println("Student added to list.");
    }

    public static void removeStudent() {
        System.out.print("Enter student's name to remove: ");
        String name = scanner.nextLine().trim().toLowerCase();
        if (students.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }

        Optional<String> student = students.stream()
                        .filter(s -> s.equalsIgnoreCase(name))
                                .findFirst();

        if (student.isPresent()) {
            students.remove(student.get());
            System.out.println("Student removed: " + student.get());
        } else {
            throw new IllegalArgumentException(name + " not on the list");
        }

    }

    public static void viewStudents(){
        if (students.isEmpty()){
            System.out.println("Students: None");
            return;
        }
        System.out.println("---Students---");
        for (String student : students) {
            System.out.println("- " + student);
        }
    }

    public static void searchStudent() {
        System.out.print("Search for a student: ");
        String student = scanner.nextLine().trim().toLowerCase();

        if (students.isEmpty()) {
            System.out.println("Empty list.");
            return;
        }

        List<String> results = students.stream()
                .filter(s -> s.toLowerCase().contains(student))
                .toList();

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Matches found: " + results);
        }
    }
}
