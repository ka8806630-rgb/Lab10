
import java.util.LinkedHashMap;

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
}
