package org.example.exception;


import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String message;

    public ValidationException(String message) {
        this.message = message;
    }
}
