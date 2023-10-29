package edu.hw4;

public class ValidationError {
    private final String message;
    private String field;

    public ValidationError(String message) {

        this(message, "");
    }

    public ValidationError(String message, String field) {
        this.message = message;
        this.field = "field: " + field;
    }

    public String getMessage() {

        return message + " " + field;
    }
}
