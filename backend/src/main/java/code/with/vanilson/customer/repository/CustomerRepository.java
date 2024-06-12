package code.with.vanilson.customer.repository;

import code.with.vanilson.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

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
