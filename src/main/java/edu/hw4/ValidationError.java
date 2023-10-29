package edu.hw4;

import java.util.Objects;

public class ValidationError {
    private final String message;
    private String field;

    public ValidationError(String message) {

        this(message, "");
    }

    public ValidationError(String message, String field) {
        this.message = message;
        this.field = field.isBlank() ? "" : "field: " + field;

    }

    public String getMessage() {

        return message + " " + field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return Objects.equals(message, that.message) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, field);
    }

    @Override
    public String toString() {
        return "ValidationError{"
                + "message='" + message + '\'' + ", field='" + field + '\'' + '}';
    }
}
