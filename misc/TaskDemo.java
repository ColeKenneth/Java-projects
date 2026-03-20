package misc;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("\nTo-Do List Menu");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Search Task");
            System.out.println("4. View Task");
            System.out.println("5. Exit");
            System.out.print("Select choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Add task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    if (priority < 1 || priority > 5) {
                        System.out.println("Invalid priority. Only choose from 1 to 5.");
                    } else {
                        tasks.add(new Task(name, priority));
                        System.out.println("Task added.");
                    }
                    break;
                case 2:
                    System.out.print("Remove task name: ");
                    String remove_task = scanner.nextLine();
                    if (tasks.isEmpty()) {
                        System.out.println("List is empty.");
                    } else {
                        boolean removed = tasks.removeIf(t -> t.name.equalsIgnoreCase(remove_task));

                        if (removed) {
                            System.out.println("Task removed!");
                        } else {
                            System.out.println("Task not in the list.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine().trim();
                    if (tasks.isEmpty()) {
                        System.out.println("List is empty.");
                    } else {
                        var filtered = tasks.stream()
                        .filter(t -> t.name.toLowerCase().contains(keyword.toLowerCase()))
                        .sorted((t1, t2) -> t2.priority - t1.priority)
                        .toList();

                        if (filtered.isEmpty()) {
                            System.out.println("No results found");
                        } else {
                            System.out.println("Result:");
                            filtered.forEach(System.out::println);
                        }
                    }
                    break;
                case 4:
                    if (tasks.isEmpty()) {
                        System.out.println("List is empty.");
                    } else {
                        System.out.println("Tasks:");

                        ArrayList<Task> sortedTasks = tasks.stream()
                            .sorted((t1, t2) -> t2.priority - t1.priority)
                            .collect(Collectors.toCollection(ArrayList::new));

                        sortedTasks.forEach(System.out::println);
                    }
                    break;
                case 5:
                    System.out.println("Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    

                    

                    

            }
        }


        scanner.close();
    }
}




class Task {
    String name;
    int priority;

    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

        @Override
        public String toString() {
            return "(Task: " + name + " - Priority: " + priority + ")";
        }
    }
