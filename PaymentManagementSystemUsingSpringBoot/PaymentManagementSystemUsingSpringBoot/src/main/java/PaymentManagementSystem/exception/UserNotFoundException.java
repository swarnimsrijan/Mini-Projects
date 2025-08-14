package PaymentManagementSystem.exception;

public class UserNotFoundException extends RuntimeException {

    // Constructor with no arguments
    public UserNotFoundException(String message) {
        super(message);
    }
}