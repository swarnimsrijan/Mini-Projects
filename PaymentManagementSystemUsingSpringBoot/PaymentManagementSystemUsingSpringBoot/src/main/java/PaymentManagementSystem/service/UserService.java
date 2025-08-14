package PaymentManagementSystem.service;

import PaymentManagementSystem.DTO.request.UserRequest;
import PaymentManagementSystem.DTO.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     * Creates a new user in the system.
     *
     * @param userRequest the details of the user to be created
     * @return the created UserResponse object
     */
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
}