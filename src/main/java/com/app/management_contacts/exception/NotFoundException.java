package com.app.management_contacts.exception;

import com.app.management_contacts.exception.ApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(String message, Object ... parameters) {
        super(HttpStatus.BAD_REQUEST, message, parameters);
    }
}
