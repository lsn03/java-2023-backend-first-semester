package edu.homework.hw2.Task3.exceptions;

public class MaxAttemptsException extends RuntimeException {
    public MaxAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxAttemptsException() {

    }

    public MaxAttemptsException(String message) {
        super(message);
    }
}
