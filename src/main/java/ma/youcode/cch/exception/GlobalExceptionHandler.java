package ma.youcode.cch.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleEntityNotFound(EntityNotFoundException e) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND.value(),
                e.getMessage()
                , LocalDateTime.now().toString());
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMessage> handleGenericException(Exception e) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorValidation> handleValidationException(MethodArgumentNotValidException e) {
        Map<String , String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach((fieldError) -> {
                    errors.put(fieldError.getField() ,fieldError.getDefaultMessage() );
                });

        ApiErrorValidation apiErrorValidation = new ApiErrorValidation(HttpStatus.BAD_REQUEST.value(), errors , LocalDateTime.now().toString());
        return new ResponseEntity<>(apiErrorValidation , HttpStatus.BAD_REQUEST);


    }


}
