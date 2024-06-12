package code.with.vanilson.customer;

import code.with.vanilson.customer.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CustomerMapper
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Slf4j
@Component
public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        var customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setRole(customer.getRole());
        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO customerDto) {
        if (customerDto == null) {
            throw new BadRequestException("cannot convert customerDto to entity");
        }

        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setRole(customerDto.getRole());
        return customer;
    }

    public static List<CustomerDTO> toDTOList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
}