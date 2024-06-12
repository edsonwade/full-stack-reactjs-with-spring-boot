package code.with.vanilson.customer.exception.handler;

/**
 * GlobalExceptionHandler
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */

import code.with.vanilson.customer.exception.BadRequestException;
import code.with.vanilson.customer.exception.CustomerNotFoundException;
import code.with.vanilson.customer.exception.CustomerWithEmailAlreadyExistsException;
import code.with.vanilson.customer.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex,
                                                                         WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerWithEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerEmailAndUsernameException(
            CustomerWithEmailAlreadyExistsException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCustomerUsernameException(
            BadRequestException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}