import java.util.Objects;

/**
 * Represents an employee registered in the attendance management system.
 */
public class Employee {
    private final String employeeId;
    private final String name;
    private final String department;

    /**
     * Creates an employee with an ID, name, and department.
     *
     * @param employeeId the employee's unique ID
     * @param name the employee's name
     * @param department the employee's department
     */
    public Employee(String employeeId, String name, String department) {
        this.employeeId = Objects.requireNonNull(employeeId, "Employee ID cannot be null.");
        this.name = Objects.requireNonNull(name, "Name cannot be null.");
        this.department = Objects.requireNonNull(department, "Department cannot be null.");
    }

    /**
     * Gets this employee's ID.
     *
     * @return the employee ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Gets this employee's name.
     *
     * @return the employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets this employee's department.
     *
     * @return the employee department
     */
    public String getDepartment() {
        return department;
    }
}
