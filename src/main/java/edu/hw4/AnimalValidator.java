package edu.hw4;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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


public class AnimalValidator {
    private static final String NAME_PATTERN = "^[A-Za-zА-Яа-я\\s]+$";
    private static final int MIN_AGE = 0;
    private static final int MIN_HEIGHT = 0;
    private static final int MIN_WEIGHT = 0;

    private static final int MAX_AGE = 20;
    private static final int MAX_HEIGHT = 200;
    private static final int MAX_WEIGHT = 100;


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


        Matcher matcher = Pattern.compile(NAME_PATTERN).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError(ERROR_NAME_IS_INVALID));
        }

        if (animal.age() < MIN_AGE) {
            errors.add(new ValidationError(ERROR_AGE_IS_NEGATIVE));
        }
        if (animal.age() > MAX_AGE) {
            errors.add(new ValidationError(ERROR_AGE_IS_HUGE));
        }
        if (animal.height() < MIN_HEIGHT) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_NEGATIVE));
        }
        if (animal.height() > MAX_HEIGHT) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_HUGE));
        }

        if (animal.weight() < MIN_WEIGHT) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_NEGATIVE));
        }
        if (animal.weight() > MAX_WEIGHT) {
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


        Matcher matcher = Pattern.compile(NAME_PATTERN).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError(ERROR_NAME_IS_INVALID, FIELD_NAME));
        }

        if (animal.age() < MIN_AGE) {
            errors.add(new ValidationError(ERROR_AGE_IS_NEGATIVE, FIELD_AGE));
        }
        if (animal.age() > MAX_AGE) {
            errors.add(new ValidationError(ERROR_AGE_IS_HUGE, FIELD_AGE));
        }
        if (animal.height() < MIN_HEIGHT) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_NEGATIVE, FIELD_HEIGHT));
        }
        if (animal.height() > MAX_HEIGHT) {
            errors.add(new ValidationError(ERROR_HEIGHT_IS_HUGE, FIELD_HEIGHT));
        }

        if (animal.weight() < MIN_WEIGHT) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_NEGATIVE, FIELD_WEIGHT));
        }
        if (animal.weight() > MAX_WEIGHT) {
            errors.add(new ValidationError(ERROR_WEIGHT_IS_HUGE, FIELD_WEIGHT));
        }

        return errors;
    }

}
