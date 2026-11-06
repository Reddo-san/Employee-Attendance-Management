import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Manages employee registration and attendance transactions.
 */
public class AttendanceManager {
    private final List<Employee> employees;
    private final List<AttendanceRecord> attendanceRecords;

    /**
     * Creates an attendance manager with empty employee and attendance lists.
     */
    public AttendanceManager() {
        employees = new ArrayList<>();
        attendanceRecords = new ArrayList<>();
    }

    /**
     * Registers an employee if their ID has not already been registered.
     *
     * @param employee the employee to register
     * @throws EmployeeAlreadyExistsException if the employee ID is already registered
     */
    public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        Objects.requireNonNull(employee, "Employee cannot be null.");
        boolean alreadyRegistered = employees.stream()
                .anyMatch(existing -> existing.getEmployeeId().equals(employee.getEmployeeId()));
        if (alreadyRegistered) {
            throw new EmployeeAlreadyExistsException(
                    "Employee ID " + employee.getEmployeeId() + " is already registered.");
        }
        employees.add(employee);
    }

    /**
     * Finds a registered employee by ID.
     *
     * @param employeeId the ID to search for
     * @return the matching employee
     * @throws EmployeeNotFoundException if no employee has the specified ID
     */
    public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee ID " + employeeId + " was not found."));
    }

    /**
     * Records the employee's time-in at the current date and time.
     *
     * @param employeeId the ID of the employee
     * @throws EmployeeNotFoundException if the employee is not registered
     * @throws InvalidAttendanceException if the employee already has an open attendance record
     */
    public void timeIn(String employeeId)
            throws EmployeeNotFoundException, InvalidAttendanceException {
        findEmployeeById(employeeId);
        boolean hasOpenRecord = attendanceRecords.stream()
                .anyMatch(record -> record.getEmployeeId().equals(employeeId)
                        && record.getTimeOut() == null);
        if (hasOpenRecord) {
            throw new InvalidAttendanceException(
                    "Employee ID " + employeeId + " already has an active attendance record.");
        }
        attendanceRecords.add(new AttendanceRecord(employeeId, LocalDateTime.now()));
    }

    /**
     * Records the employee's time-out at the current date and time.
     *
     * @param employeeId the ID of the employee
     * @throws EmployeeNotFoundException if the employee is not registered
     * @throws InvalidAttendanceException if the employee has no open attendance record
     */
    public void timeOut(String employeeId)
            throws EmployeeNotFoundException, InvalidAttendanceException {
        findEmployeeById(employeeId);
        AttendanceRecord activeRecord = attendanceRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId)
                        && record.getTimeOut() == null)
                .findFirst()
                .orElseThrow(() -> new InvalidAttendanceException(
                        "Employee ID " + employeeId + " has no active attendance record."));
        activeRecord.setTimeOut(LocalDateTime.now());
    }

    /**
     * Gets all completed attendance records for a registered employee.
     *
     * @param employeeId the ID of the employee
     * @return completed attendance records belonging to the employee
     * @throws EmployeeNotFoundException if the employee is not registered
     */
    public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
            throws EmployeeNotFoundException {
        findEmployeeById(employeeId);
        return attendanceRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId)
                        && record.getTimeOut() != null)
                .collect(Collectors.toList());
    }
}
