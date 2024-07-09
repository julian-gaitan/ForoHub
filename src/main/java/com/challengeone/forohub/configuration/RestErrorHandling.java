package com.challengeone.forohub.configuration;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestErrorHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> validationError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException ex) {
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        DataErrorValidation error = new DataErrorValidation(ex.getClass().getSimpleName(), message);
        return ResponseEntity.badRequest().body(error);
    }

    public record DataErrorValidation(
            String field,
            String message
    ) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
