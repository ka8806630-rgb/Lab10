
import java.util.LinkedHashMap;
import java.util.Comparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class HR {
    private LinkedHashMap<Integer, Employee> records;

    public HR(int capacity) {
        records = new LinkedHashMap<>();
    }

    public boolean addPerson(Employee person) {
        if (records.containsKey(person.getId())) {
            System.out.println("Employee ID already exists.");
            return false;
        }

        records.put(person.getId(), person);
        System.out.println("Person added successfully.");
        return true;
    }

    public void removeEmployee(int id) {
        if (records.remove(id) != null) {
            System.out.println("Person removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateSalary(int id, double newSalary) {
        Employee person = records.get(id);

        if (person == null) {
            System.out.println("Employee not found.");
        } else if (person instanceof Student) {
            System.out.println("Cannot update salary for Student.");
        } else {
            person.setSalary(newSalary);
            System.out.println("Salary updated successfully.");
        }
    }

    public void displayAll() {
        int students = 0;
        int employees = 0;

        System.out.println("\n------ HR Records ------");

        for (Employee person : records.values()) {
            System.out.println(person);

            if (person instanceof Student) {
                students++;
            } else {
                employees++;
            }
        }

        System.out.println("------------------------");
        System.out.println("Total Students: " + students);
        System.out.println("Total Employees: " + employees);
        System.out.println("Total Persons: " + records.size());
    }

    public void saveData() {
        try (FileWriter writer = new FileWriter("employees.txt")) {
            for (Employee person : records.values()) {
                String role = (person instanceof Student) ? "Student" : "Employee";

                writer.write(role + "," + person.getId() + ","
                        + person.getName() + ","
                        + person.getAge() + ","
                        + person.getSalary() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("employees.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length != 5) {
                    continue;
                }

                String role = data[0];
                int id = Integer.parseInt(data[1]);
                String name = data[2];
                int age = Integer.parseInt(data[3]);
                double salary = Double.parseDouble(data[4]);

                Employee person;

                if (role.equals("Student")) {
                    person = new Student(id, name, age);
                } else if (role.equals("Employee")) {
                    person = new Employee(id, name, age, salary);
                    person.currency = new USDollar();
                } else {
                    continue;
                }

                records.put(id, person);
            }

            System.out.println("Employee data loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("No saved employee data found.");
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in saved data.");
        }
    }

    public void displayHighSalaryEmployees() {
        System.out.println("\n--- Employees with Salary > 5000 ---");

        records.values().stream()
                .filter(person -> !(person instanceof Student))
                .filter(person -> person.getSalary() > 5000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(person -> System.out.println(person));
    }

}
