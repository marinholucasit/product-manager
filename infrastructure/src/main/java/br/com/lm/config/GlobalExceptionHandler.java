package br.com.lm.config;

import br.com.lm.dto.response.ErrorResponse;
import br.com.lm.exception.InvalidProductException;
import br.com.lm.exception.ProductAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProductAlreadyExists(ProductAlreadyExistsException ex) {
        var response = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), "Product Already Exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProduct(InvalidProductException ex) {
        var response = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), "Invalid Product");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        var response = new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
