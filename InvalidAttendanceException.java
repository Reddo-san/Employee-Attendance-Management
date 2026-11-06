/**
 * Indicates that an attendance operation violates the attendance rules.
 */
public class InvalidAttendanceException extends Exception {
    /**
     * Creates an exception with a descriptive message.
     *
     * @param message the exception message
     */
    public InvalidAttendanceException(String message) {
        super(message);
    }
}
