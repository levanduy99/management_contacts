package com.app.management_contacts.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends Exception {

    private Object[] parameters;

    private Object data;

    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public ApiException(HttpStatus statusCode, String message, Object... parameters) {
        super(message);
        this.statusCode = statusCode;
        this.parameters = parameters;
    }

    public ApiException(String message) {
        super(message);
        if (this.getClass().getName().contains("NotFound")) {
            statusCode = HttpStatus.NOT_FOUND;
        }
    }
}
