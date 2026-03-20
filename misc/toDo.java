package misc;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class toDo {
    private static final Scanner sc = new Scanner(System.in);
    private final ArrayList<String> todo_list = new ArrayList<>();
    public static void main(String[] args) {
        String choice;
        toDo app = new toDo();
        
        do {
            System.out.println("\nTASK MENU");
            System.out.println("A. Add Task \nB. Remove Task \nC. View Tasks \nD. Exit");
            System.out.print("Enter a choice: ");
            choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    app.addTodo();
                    break;
                case "B":
                    app.removeTodo();
                    break;
                case "C":
                    app.viewTodo();
                    break;
                case "D":
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            
        } while (!choice.equals("D"));
        
    }

    public void addTodo() {
        System.out.print("Enter task: ");
        String task = sc.nextLine().trim().toLowerCase();

        if (todo_list.contains(task)) {
            System.out.println("Task already exists!");
        }

        todo_list.add(task);
        System.out.println("Task added!");
    }

    public void removeTodo() {
        System.out.print("Enter task to remove: ");
        String task = sc.nextLine().trim().toLowerCase();

        if (todo_list.isEmpty()) {
            System.out.println("List is empty!");
        }

        if (!todo_list.contains(task)) {
            System.out.println("Task not in the list!");
        } else {
            todo_list.remove(task);
            System.out.println("Task removed!");
        }
    }

    public void viewTodo() {
        Iterator<String> viewList = todo_list.iterator();
        System.out.println("Tasks:");

        while (viewList.hasNext()) {
            System.out.println(viewList.next());
        }
    }
}
