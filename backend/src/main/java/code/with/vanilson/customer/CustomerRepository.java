package code.with.vanilson.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CustomerRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findCustomerByUsername(String username);
}
