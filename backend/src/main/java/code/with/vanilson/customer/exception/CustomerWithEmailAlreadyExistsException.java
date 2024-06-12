package code.with.vanilson.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomerWithEmailAlreadyExistsException
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerWithEmailAlreadyExistsException extends RuntimeException {
    public CustomerWithEmailAlreadyExistsException(String message) {
        super(message);
    }
}