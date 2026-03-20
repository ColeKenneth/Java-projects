package misc;

import java.util.Scanner;

public class mainAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        accountList account = new accountList();
        String choice = "";
        

        do {
            System.out.println("\nMENU");
            System.out.println("A. Create Account");
            System.out.println("B. Remove Account");
            System.out.println("C. View Accounts");
            System.out.println("D. Exit");
            System.out.print("Select what you want to do: ");
            choice = sc.nextLine().trim().toUpperCase();
            

            switch (choice) {
                case "A":
                case "a":
                    System.out.print("Create account type (Student/Teacher): ");
                    String type = sc.nextLine().trim();

                    if (!type.equalsIgnoreCase("Student") && !type.equalsIgnoreCase("Teacher")) {
                        System.out.println("Invalid type!");
                        return;
                    }

                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    if (!email.contains("@")) {
                        throw new IllegalArgumentException("Invalid email format!");
                    } else {
                        System.out.print("Password: ");
                        String password = sc.nextLine().trim();

                        System.out.println("Confirm password: ");
                        String confirm = sc.nextLine();

                        if (!confirm.equals(password)) {
                            System.out.println("Password do not match!");
                        } else {
                            if (type.equalsIgnoreCase("Student")) {
                                System.out.print("Enter year level: ");
                                String level = sc.nextLine();

                                System.out.print("Student Number: ");
                                String studentNumber = sc.nextLine();

                                Student student = new Student(name, email, password, level, studentNumber);
                                account.addAccount(student);
                                System.out.println("Student added successfully!");
                            } else if (type.equalsIgnoreCase("Teacher")) {
                                System.out.print("Enter salary: ");
                                double salary = sc.nextDouble();
                                sc.nextLine();

                                Teacher teacher = new Teacher(name, email, password, salary);
                                account.addAccount(teacher);
                                System.out.println("Teacher added successfully!");
                            }
                    }
                }
                    break;
                case "B": 
                case "b":
                    System.out.print("Enter an account you want to remove: ");
                    String removeName = sc.nextLine();

                    account.removeAccount(removeName);
                    break;
                case "C":
                case "c":
                    account.showAccounts();
                    break;
                case "D":
                case "d":
                    System.out.println("Thank you!");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice.");

            }
            
        } while (!choice.equalsIgnoreCase("D"));



        

        sc.close();
    }
}
 