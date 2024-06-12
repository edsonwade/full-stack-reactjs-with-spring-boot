package code.with.vanilson.customer.service;

import code.with.vanilson.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomerDetails
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Slf4j
@Service
public class CustomerDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
    }
}