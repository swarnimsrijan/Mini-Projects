package PaymentManagementSystem.repository;

import PaymentManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user
     * @return an Optional containing the User if found, or empty if not found
     */
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
