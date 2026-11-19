package com.omniai.exception;

public class EndpointConflictException extends RuntimeException {

    public EndpointConflictException(String message) {
        super(message);
    }
}