package studentManagement;

public class Student {
    private int studentID;
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private YearLevel yearLevel;

    public Student(int studentID, String firstName, String middleName, String lastName, int age, YearLevel yearLevel) {
        setStudentID(studentID);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setAge(age);
        setYearLevel(yearLevel);
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        if (studentID <= 0) {
            throw new IllegalArgumentException("Student ID cannot be zero or a negative value.");
        }
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("Your first name is required!");
        }

        if (firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }

        this.firstName = firstName.trim();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null) {
            this.middleName = "";
            return;
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Your last name is required.");
        }

        if (lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 10 || age > 100) {
            throw new IllegalArgumentException("Invalid age!");
        }

        this.age = age;
    }

    public YearLevel getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(YearLevel yearLevel) {
        if (yearLevel == null) {
            throw new IllegalArgumentException("Year level cannot be null");
        }
        this.yearLevel = yearLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Information: \n");
        sb.append("Student ID: ").append(getStudentID()).append("\n");
        sb.append("First Name: ").append(getFirstName()).append("\n");
        sb.append("Middle Name: ").append(getMiddleName()).append("\n");
        sb.append("Last Name: ").append(getLastName()).append("\n");
        sb.append("Age: ").append(getAge()).append("\n");
        sb.append("Year Level: ").append(getYearLevel());

        return sb.toString();
    }
}
