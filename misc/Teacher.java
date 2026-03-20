package misc;

public class Teacher extends Account {
    private double salary;

    public Teacher(String name, String email, String password, double salary) {
        super(name, password, email);
        this.salary = salary;
    }

    @Override
    public void showRole() {
        System.out.println(getName() + " is a teacher.");
    }
}
