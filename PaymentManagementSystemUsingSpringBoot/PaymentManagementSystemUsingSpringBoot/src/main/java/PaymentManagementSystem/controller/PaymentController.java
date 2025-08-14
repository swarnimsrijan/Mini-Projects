package PaymentManagementSystem.controller;

import PaymentManagementSystem.DTO.request.PaymentRequest;
import PaymentManagementSystem.DTO.response.ApiResponse;
import PaymentManagementSystem.DTO.response.PaymentResponse;
import PaymentManagementSystem.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is part of the Payment Management System project, which uses Spring Boot for backend development.
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // This class is part of the Payment Management System project, which uses Spring Boot for backend development.
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE_MANAGER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(
            @Valid @RequestBody PaymentRequest paymentRequest, Authentication authentication) {
        PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Payment created successfully", paymentResponse));
    }

    // This method creates a new payment record in the system.
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE_MANAGER', 'VIEWER')")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        List<PaymentResponse> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(ApiResponse.success("Payments retrieved successfully", payments));
    }

    // This method retrieves all payment records from the system.
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE_MANAGER', 'VIEWER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable Long id) {
        PaymentResponse paymentResponse = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success("Payment retrieved successfully", paymentResponse));
    }

    // This method retrieves a specific payment record by its ID.
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE_MANAGER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(
            @PathVariable Long id, @Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.updatePayment(id, paymentRequest);
        return ResponseEntity.ok(ApiResponse.success("Payment updated successfully", paymentResponse));
    }

    // This method updates an existing payment record in the system.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok(ApiResponse.success("Payment deleted successfully", "Payment with ID " + id + " has been deleted"));
    }
}