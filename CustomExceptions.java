/**
 * Indicates that an employee ID is already registered.
 */
class EmployeeAlreadyExistsException extends Exception {
    /**
     * Creates an exception with a descriptive message.
     *
     * @param message the exception message
     */
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}

/**
 * Indicates that the requested employee ID is not registered.
 */
class EmployeeNotFoundException extends Exception {
    /**
     * Creates an exception with a descriptive message.
     *
     * @param message the exception message
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

/**
 * Indicates that an attendance operation violates the attendance rules.
 */
class InvalidAttendanceException extends Exception {
    /**
     * Creates an exception with a descriptive message.
     *
     * @param message the exception message
     */
    public InvalidAttendanceException(String message) {
        super(message);
    }
}
