package code.with.vanilson.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomerNotFoundException
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}