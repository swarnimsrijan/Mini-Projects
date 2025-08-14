package PaymentManagementSystem.service;

import PaymentManagementSystem.DTO.request.PaymentRequest;
import PaymentManagementSystem.DTO.response.PaymentResponse;
import PaymentManagementSystem.entity.Payment;
import PaymentManagementSystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;

public interface PaymentService {
    /**
     * Creates a new payment record in the system.
     *
     * @param paymentRequest the details of the payment to be created
     * @param userEmail      the email of the user creating the payment
     * @return the created PaymentResponse object
     */
    PaymentResponse createPayment(PaymentRequest paymentRequest, String userEmail);
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(Long id);
    PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest);
    void deletePayment(Long id);
}