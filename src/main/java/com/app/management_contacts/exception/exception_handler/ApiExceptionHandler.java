package com.app.management_contacts.exception.exception_handler;

import com.app.management_contacts.dto.response.MessageResponse;
import com.app.management_contacts.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public final ResponseEntity<MessageResponse<Void>> handleGenericException(Exception ex) {
        logger.error("Internal exception: ", ex);
        return new ResponseEntity<>(
                MessageResponse.ofError(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Oops! We are going to process this problem. Please come back later!",
                        null), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ResponseEntity<MessageResponse<Map<String, String>>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        logger.warn("Bad request: ", ex);
        return new ResponseEntity<>(
                MessageResponse.ofError(HttpStatus.BAD_REQUEST.value(), ex.getCause().getLocalizedMessage(), null),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<MessageResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        logger.warn("Bad request: ", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                MessageResponse.ofError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Arguments", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<MessageResponse<?>> handleVaToException(ApiException ex) {
        logger.warn("Bad request:", ex);
        String message = ex.getMessage() != null ? ex.getMessage() : ex.getClass().getName();
        return new ResponseEntity<>(MessageResponse.ofError(ex.getStatusCode().value(), message, null), HttpStatus.OK);
    }
}
