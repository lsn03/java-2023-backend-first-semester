package edu.homework.hw4;

import java.util.Objects;

public class ValidationError {
    private final ErrorMessages message;
    private ErrorsFields field;

    public ValidationError(ErrorMessages message) {

        this(message, null);
    }

    public ValidationError(ErrorMessages message, ErrorsFields field) {
        this.message = message;
        this.field =  field;

    }

    public String getMessage() {

        return message.name() + " " + field.name();
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
