package edu.sjsu.cmpe282.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceCreateException: handle create object conflict exception
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceCreateException extends RuntimeException {

    public ResourceCreateException(String message) {
        super(message);
    }
}
