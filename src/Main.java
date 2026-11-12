
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HR hrSystem = new HR(10);

        int choice;

        do {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee or Student");
            System.out.println("2. Remove Employee by ID");
            System.out.println("3. Display All Records");
            System.out.println("4. Update Salary");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Salary (0 for Student): ");
                    double salary = Double.parseDouble(scanner.nextLine());

                    Employee person;

                    if (salary == 0) {
                        person = new Student(id, name, age);
                    } else {
                        person = new Employee(id, name, age, salary);
                        person.currency = new USDollar();
                    }

                    hrSystem.addPerson(person);
                    break;

                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = Integer.parseInt(scanner.nextLine());
                    hrSystem.removeEmployee(removeId);
                    break;

                case 3:
                    hrSystem.displayAll();
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter New Salary: ");
                    double newSalary = Double.parseDouble(scanner.nextLine());

                    hrSystem.updateSalary(updateId, newSalary);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
