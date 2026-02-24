package studentManagement;

import java.util.HashMap;

public class StudentStorage {
    private HashMap<Integer, Student> students = new HashMap<>();

    private Student findStudent(int studentID) {
        Student student = students.get(studentID);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + studentID + " not found");
        }
        return student;
    }

    public void addStudent(Student newStudent) {
        if (students.containsKey(newStudent.getStudentID())) {
            throw new StudentNotFoundException("Student with a Student ID of: " + newStudent.getStudentID() + " already exists");
        }
        students.put(newStudent.getStudentID(), newStudent);
        System.out.println("Student added successfully.");
    }



    public void updateStudentName(int studentID, String firstName, String lastName) {
        Student student = findStudent(studentID);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        System.out.println("Name updated!");
    }

    public void updateStudentAge(int studentID, int age) {
        Student student = students.get(studentID);
        if (age < 17 || age > 100) {
            throw new IllegalArgumentException("Invalid age: " + age + ". Age must be between 17 and 100.");
        }
        student.setAge(age);
        System.out.println("Age updated!");
    }

    public void updateYearLevel(int studentID, YearLevel yearLevel) {
        findStudent(studentID).setYearLevel(yearLevel);
        System.out.println("Year Level updated.");
    }

    public void deleteStudent(int studentID) {
        findStudent(studentID);
        students.remove(studentID);
        System.out.println("Student deleted.");
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }





}
