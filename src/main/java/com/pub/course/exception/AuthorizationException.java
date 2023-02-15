package com.pub.course.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
