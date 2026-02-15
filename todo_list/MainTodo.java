package todo_list;
import java.util.Scanner;


public class MainTodo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();
        
        boolean running = true;

        while (running) {
            System.out.println("\n---To-Do List Menu---");
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
                    System.out.print("Add Task: ");
                    String task = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    todoList.addTask(task, priority);
                    break;
                case 2:
                    System.out.print("Enter task to remove: ");
                    String removeTask = scanner.nextLine();
                    todoList.removeTask(removeTask);
                    break;
                case 3:
                    System.out.print("Search or enter a keyword: ");
                    String keyword = scanner.nextLine();
                    todoList.SearchTask(keyword);
                    break;
                case 4:
                    todoList.ViewTask();
                    break;
                case 5:
                    System.out.println("Session ended.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
    
}
