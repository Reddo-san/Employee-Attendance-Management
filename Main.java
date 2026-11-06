import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Demonstrates the functionality of the Employee Attendance Management System
 * by executing test cases 1 through 10.
 */
public class Main {

    /**
     * Main entry point to run all test cases.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Test Case 1: Register employees
        System.out.println("--- Test Case 1: Register employees ---");
        try {
            manager.registerEmployee(new Employee("E001", "Riri Williams", "IT"));
            System.out.println("Employee E001 successfully registered.");
            manager.registerEmployee(new Employee("E002", "Nick Fury", "HR"));
            System.out.println("Employee E002 successfully registered.");
        } catch (EmployeeAlreadyExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 2: Prevent duplicate employee registration
        System.out.println("\n--- Test Case 2: Prevent duplicate employee registration ---");
        try {
            manager.registerEmployee(new Employee("E001", "Thor Son of Odin", "Electrical"));
        } catch (EmployeeAlreadyExistsException e) {
            System.out.println("EmployeeAlreadyExistsException caught: " + e.getMessage());
        }

        // Test Case 3: Find an existing employee
        System.out.println("\n--- Test Case 3: Find an existing employee ---");
        try {
            Employee emp = manager.findEmployeeById("E001");
            System.out.println("Employee Details - ID: " + emp.getEmployeeId() 
                    + ", Name: " + emp.getName() 
                    + ", Department: " + emp.getDepartment());
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 4: Handle an unregistered employee
        System.out.println("\n--- Test Case 4: Handle an unregistered employee ---");
        try {
            manager.findEmployeeById("E999");
        } catch (EmployeeNotFoundException e) {
            System.out.println("EmployeeNotFoundException caught: " + e.getMessage());
        }

        // Test Case 5: Record an employee's time-in
        System.out.println("\n--- Test Case 5: Record an employee's time-in ---");
        try {
            manager.timeIn("E001");
            Employee emp = manager.findEmployeeById("E001");
            System.out.println("A new attendance record is created for " + emp.getName() + " (E001).");
            
            LocalDateTime displayedTimeIn = LocalDateTime.now();
            System.out.println("Attendance Record Created:");
            System.out.println("timeIn: " + displayedTimeIn.format(formatter));
            System.out.println("timeOut: null");

        } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 6: Prevent multiple active attendance records
        System.out.println("\n--- Test Case 6: Prevent multiple active attendance records ---");
        try {
            manager.timeIn("E001");
        } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
            System.out.println("InvalidAttendanceException caught: " + e.getMessage());
        }

        // Test Case 7: Prevent time-out without time-in / active record
        System.out.println("\n--- Test Case 7: Prevent time-out without active record ---");
        try {
            manager.timeOut("E002");
        } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
            System.out.println("InvalidAttendanceException caught: " + e.getMessage());
        }

        // Test Case 8: Record an employee's time-out
        System.out.println("\n--- Test Case 8: Record an employee's time-out ---");
        try {
            manager.timeOut("E001");
            System.out.println("The existing active attendance record is updated with current date and time as timeOut.");
            System.out.println("Time Out recorded at: " + java.time.LocalDateTime.now().format(formatter));
        } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 9: Allow a new attendance record after completing a previous one
        System.out.println("\n--- Test Case 9: Allow a new attendance record ---");
        try {
            manager.timeIn("E001");
            System.out.println("A new attendance record is created for E001.");
            System.out.println("Attendance Record Created:");
            System.out.println("timeIn: " + LocalDateTime.now().format(formatter));
            System.out.println("timeOut: null");
        } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 10: Filter completed attendance records using a lambda expression
        System.out.println("\n--- Test Case 10: Filter completed attendance records ---");
        try {
            List<AttendanceRecord> completedRecords = manager.getCompletedAttendanceRecords("E001");
            System.out.println("Completed Attendance Records for E001:");
            if (completedRecords.isEmpty()) {
                System.out.println("No completed records found.");
            } else {
                for (AttendanceRecord record : completedRecords) {
                    String timeInStr = record.getTimeIn().format(formatter);
                    String timeOutStr = record.getTimeOut().format(formatter);
                    System.out.println("ID: " + record.getEmployeeId() 
                            + " | Time In: " + timeInStr 
                            + " | Time Out: " + timeOutStr);
                }
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}