package PaymentManagementSystem.controller;


import PaymentManagementSystem.DTO.request.UserRequest;
import PaymentManagementSystem.DTO.response.ApiResponse;
import PaymentManagementSystem.DTO.response.UserResponse;
import PaymentManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// This class is part of the Payment Management System project, which uses Spring Boot for backend development.
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // This class is part of the Payment Management System project, which uses Spring Boot for backend development.
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.ok(ApiResponse.success("User created successfully", userResponse));
    }

    // This method creates a new user in the system.
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }
}
