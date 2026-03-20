package misc;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Scanner;


public class stream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> todo = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\nSelect what to do with task: ");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Search in the List");
            System.out.println("4. View List");
            System.out.println("5. Exit");
            System.out.print("Pick a choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter task: ");
                String task = scanner.nextLine();
                todo.add(task);
                System.out.println("Task added successfully.");
            } else if (choice == 2) {
                System.out.print("Remove a task: ");
                String task = scanner.nextLine();
                if (todo.isEmpty()) {
                    System.out.println("List is empty.");
                } else if (!todo.contains(task)) {
                    System.out.println("Task not in the list.");
                } else {
                    todo.remove(task);
                    System.out.println("Task removed");
                }
            } else if (choice == 3) {
                System.out.println("Enter keyword: ");
                String keyword = scanner.nextLine();
                if (todo.isEmpty()) {
                    System.out.println("List is empty");
                } else {
                    ArrayList<String> todoList = todo.stream()
                    .map(String::toUpperCase)
                    .filter(task -> task.contains(keyword.toUpperCase()))
                    .sorted()
                    .collect(Collectors.toCollection(ArrayList::new));
                
                    System.out.println("\nResult:");
                    todoList.forEach(task -> System.out.println("- " + task));
                }
            } else if (choice == 4) {
                if (todo.isEmpty()) {
                    System.out.println("List is empty.");
                } else {
                    ArrayList<String> todoList = todo.stream()
                    .map(String::toUpperCase)
                    .sorted()
                    .collect(Collectors.toCollection(ArrayList::new));

                    System.out.println("\nYour Tasks:");
                    todoList.forEach(task -> System.out.println("- " + task));
                }
            } else if (choice == 5) {
                System.out.println("Thank you!");
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
