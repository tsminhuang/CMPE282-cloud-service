package edu.sjsu.cmpe282.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceConflictException: handle create object conflict exception
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
