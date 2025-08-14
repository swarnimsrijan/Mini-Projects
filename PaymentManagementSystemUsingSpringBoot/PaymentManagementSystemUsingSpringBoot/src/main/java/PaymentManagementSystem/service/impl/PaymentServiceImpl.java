package PaymentManagementSystem.service.impl;

import PaymentManagementSystem.DTO.request.PaymentRequest;
import PaymentManagementSystem.DTO.response.PaymentResponse;
import PaymentManagementSystem.entity.Payment;
import PaymentManagementSystem.entity.User;
import PaymentManagementSystem.exception.PaymentNotFoundException;
import PaymentManagementSystem.exception.UserNotFoundException;
import PaymentManagementSystem.repository.PaymentRepository;
import PaymentManagementSystem.repository.UserRepository;
import PaymentManagementSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new payment record in the system.
     *
     * @param paymentRequest the details of the payment to be created
     * @param userEmail      the email of the user creating the payment
     * @return the created PaymentResponse object
     */
    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + userEmail));

        Payment payment = new Payment(
                paymentRequest.getAmount(),
                paymentRequest.getPaymentType(),
                paymentRequest.getCategory(),
                paymentRequest.getStatus(),
                LocalDateTime.now(),
                user
        );

        Payment savedPayment = paymentRepository.save(payment);
        return mapToResponse(savedPayment);
    }

    /**
     * Retrieves all payment records from the system.
     *
     * @return a list of PaymentResponse objects
     */
    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific payment record by its ID.
     *
     * @param id the ID of the payment to retrieve
     * @return the PaymentResponse object for the specified payment
     */
    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        return mapToResponse(payment);
    }

    /**
     * Updates an existing payment record in the system.
     *
     * @param id              the ID of the payment to update
     * @param paymentRequest  the new details for the payment
     * @return the updated PaymentResponse object
     */
    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentType(paymentRequest.getPaymentType());
        payment.setCategory(paymentRequest.getCategory());
        payment.setStatus(paymentRequest.getStatus());

        Payment updatedPayment = paymentRepository.save(payment);
        return mapToResponse(updatedPayment);
    }

    /**
     * Deletes a payment record from the system.
     *
     * @param id the ID of the payment to delete
     */
    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    /**
     * Maps a Payment entity to a PaymentResponse DTO.
     *
     * @param payment the Payment entity to map
     * @return the mapped PaymentResponse DTO
     */
    private PaymentResponse mapToResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentType(),
                payment.getCategory(),
                payment.getStatus(),
                payment.getDate(),
                payment.getCreatedBy().getName()
        );
    }
}