package todo_list;

public class todo {
    private String task;
    private int priority;

    public todo(String task, int priority) {
        this.task = task;
        this.priority = priority;
    }

    public String getTask() {
        return task;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return task + " - " + priority;
    }
}
