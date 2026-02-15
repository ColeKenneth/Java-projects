package todo_list;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TodoList {
    private ArrayList<todo> tasks = new ArrayList<>();

    public void addTask(String task, int priority) {
       if (priority < 1 || priority > 5) {
        System.out.println("Priority must be from 1-5 only.");
       } else {
        tasks.add(new todo(task, priority));
        System.out.println("Task added successfully.");
       }

    }
    


    public void removeTask(String task) {
        if (tasks.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            boolean removed = tasks.removeIf(t -> t.getTask().equalsIgnoreCase(task));
            System.out.println(removed ? "Task removed" : "Task not in the list.");
        }
    }

    public void SearchTask(String keyword) {
        ArrayList<todo> result = tasks.stream()
        .filter(t -> t.getTask().toLowerCase().contains(keyword.toLowerCase()))
        .collect(Collectors.toCollection(ArrayList::new));

        if (result.isEmpty()) {
            System.out.println("No result found.");
        } else {
            System.out.println("Result:");
            System.out.println("----------");
            result.forEach(System.out::println);
        }
    }

    public void ViewTask() {
        if (tasks.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            ArrayList<todo> todo_list = tasks.stream()
            .sorted((a, b) -> a.getPriority() - b.getPriority())
            .collect(Collectors.toCollection(ArrayList::new));

            System.out.println("To-Do List:");
            System.out.println("-----------");
            todo_list.forEach(System.out::println);
        }
    }


}
