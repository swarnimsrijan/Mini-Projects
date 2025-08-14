package PaymentManagementSystem.exception;

public class PaymentNotFoundException extends RuntimeException {

    // Constructor with no arguments
    public PaymentNotFoundException(String message) {
        super(message);
    }
}