package code.with.vanilson.customer.dto;

import code.with.vanilson.customer.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * CustomerDTO
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Data
public class CustomerDTO {
    @NotNull(message = "The unique identifier for the customer.")
    Long id;

    @NotEmpty(message = "The first name of the customer should not be empty.")
    String firstName;

    @NotEmpty(message = "The last name of the customer should not be empty.")
    String lastName;

    @NotEmpty(message = "The username of the customer should not be empty.")
    String username;

    @NotEmpty(message = "The password of the customer should not be empty.")
    String password;

    @NotEmpty(message = "The email address of the customer should not be empty.")
    String email;

    String phone;

    @NotNull(message = "The role of the customer should not be null.")
    Role role;
}