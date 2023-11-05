package edu.homework.hw2.Task3.exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException() {

    }

    public ConnectionException(String message) {
        super(message);
    }
}
