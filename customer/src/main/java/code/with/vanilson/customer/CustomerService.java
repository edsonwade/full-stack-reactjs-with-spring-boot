package code.with.vanilson.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest customerRegister) {
        Customer customer = Customer.builder()
                .firstName(customerRegister.firstName())
                .lastName(customerRegister.lastName())
                .email(customerRegister.email())
                .build();
        // todo: check if email valid
        customerRepository.saveAndFlush(customer);
        // todo: check if email not taken

        var fraudCheckResponse = restTemplate
                .getForObject("http://localhost:8082/api/v1/fraud-check/{customerId]}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (Objects.requireNonNull(fraudCheckResponse).isFraudster()) {
            throw new IllegalArgumentException("fraudster");

        }

    }
}
