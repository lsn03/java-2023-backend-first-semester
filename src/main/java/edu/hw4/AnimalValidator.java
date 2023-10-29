package edu.hw4;

import static edu.hw4.Errors.ERROR_AGE_IS_HUGE;
import static edu.hw4.Errors.ERROR_AGE_IS_NEGATIVE;
import static edu.hw4.Errors.ERROR_HEIGHT_IS_HUGE;
import static edu.hw4.Errors.ERROR_HEIGHT_IS_NEGATIVE;
import static edu.hw4.Errors.ERROR_NAME_IS_BLANK;
import static edu.hw4.Errors.ERROR_NAME_IS_INVALID;
import static edu.hw4.Errors.ERROR_NAME_IS_NULL;
import static edu.hw4.Errors.ERROR_WEIGHT_IS_HUGE;
import static edu.hw4.Errors.ERROR_WEIGHT_IS_NEGATIVE;
import static edu.hw4.Errors.FIELD_AGE;
import static edu.hw4.Errors.FIELD_HEIGHT;
import static edu.hw4.Errors.FIELD_NAME;
import static edu.hw4.Errors.FIELD_WEIGHT;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnimalValidator {
    public Map<String, Set<ValidationError>> validateTask19(List<Animal> list) {

        AnimalUtilities.nullChecker(list);


        return list.stream().collect(Collectors.toMap(Animal::name, this::validateAnimalTask19));
    }

    public Map<String, String> validateTask20(List<Animal> list) {

        AnimalUtilities.nullChecker(list);


        return list.stream().collect(
                Collectors.toMap(Animal::name, animal -> {
                    Set<ValidationError> errors = validateAnimalTask20(animal);
                    return errors.stream().map(ValidationError::getMessage).collect(Collectors.joining(", "));
                })
        );
    }

    private Set<ValidationError> validateAnimalTask19(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name() == null) {
            errors.add(new ValidationError(ERROR_NAME_IS_NULL));
            return errors;
        }

        if (animal.name().isBlank()) {
            errors.add(new ValidationError(ERROR_NAME_IS_BLANK));
        }

        String regExp = "^[A-Za-zА-Яа-я\\s]+$";
        Matcher matcher = Pattern.compile(regExp).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError(ERROR_NAME_IS_INVALID));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError(ERROR_AGE_IS_NEGATIVE));
        }
        if (animal.age() > 20) {
            errors.add(new ValidationError(ERROR_AGE_IS_HUGE));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_NEGATIVE));
        }
        if (animal.height() > 200) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_HUGE));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_NEGATIVE));
        }
        if (animal.weight() > 100) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_HUGE));
        }

        return errors;
    }

    private Set<ValidationError> validateAnimalTask20(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name() == null) {
            errors.add(new ValidationError(ERROR_NAME_IS_NULL, FIELD_NAME));
            return errors;
        }

        if (animal.name().isBlank()) {
            errors.add(new ValidationError(ERROR_NAME_IS_BLANK, FIELD_NAME));
        }

        String regExp = "^[A-Za-zА-Яа-я\\s]+$";
        Matcher matcher = Pattern.compile(regExp).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError(ERROR_NAME_IS_INVALID, FIELD_NAME));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError(ERROR_AGE_IS_NEGATIVE, FIELD_AGE));
        }
        if (animal.age() > 20) {
            errors.add(new ValidationError(ERROR_AGE_IS_HUGE, FIELD_AGE));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_NEGATIVE, FIELD_HEIGHT));
        }
        if (animal.height() > 200) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_HUGE, FIELD_HEIGHT));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_NEGATIVE, FIELD_WEIGHT));
        }
        if (animal.weight() > 100) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_HUGE, FIELD_WEIGHT));
        }

        return errors;
    }

}
