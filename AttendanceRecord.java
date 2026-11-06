import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an employee's time-in and time-out transaction.
 */
public class AttendanceRecord {
    private final String employeeId;
    private final LocalDateTime timeIn;
    private LocalDateTime timeOut;

    /**
     * Creates an attendance record with no time-out value yet.
     *
     * @param employeeId the ID of the employee this record belongs to
     * @param timeIn the date and time the employee started work
     */
    public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
        this.employeeId = Objects.requireNonNull(employeeId, "Employee ID cannot be null.");
        this.timeIn = Objects.requireNonNull(timeIn, "Time-in cannot be null.");
        this.timeOut = null;
    }

    /**
     * Gets the ID of the employee this record belongs to.
     *
     * @return the employee ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Gets the time-in date and time.
     *
     * @return the time-in value
     */
    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    /**
     * Gets the time-out date and time.
     *
     * @return the time-out value, or {@code null} if the record is not complete
     */
    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    /**
     * Sets the time-out date and time for this record.
     *
     * @param timeOut the time-out value
     */
    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = Objects.requireNonNull(timeOut, "Time-out cannot be null.");
    }
}
