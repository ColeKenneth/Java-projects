package misc;

public class Student extends Account {
    private String yearLevel;
    private String studentNumber;

    public Student(String name, String email, String password, String yearLevel, String studentNumber) {
        super(name, email, password);
        this.yearLevel = yearLevel;
        this.studentNumber = studentNumber;
    }

    @Override
    public void showRole() {
        System.out.println(getName() + " is a student.");
    }
}
