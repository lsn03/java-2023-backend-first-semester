package edu.homework.hw4;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static edu.homework.hw4.ErrorMessages.ERROR_AGE_IS_HUGE;
import static edu.homework.hw4.ErrorMessages.ERROR_AGE_IS_NEGATIVE;
import static edu.homework.hw4.ErrorMessages.ERROR_HEIGHT_IS_HUGE;
import static edu.homework.hw4.ErrorMessages.ERROR_HEIGHT_IS_NEGATIVE;
import static edu.homework.hw4.ErrorMessages.ERROR_NAME_IS_BLANK;
import static edu.homework.hw4.ErrorMessages.ERROR_NAME_IS_INVALID;
import static edu.homework.hw4.ErrorMessages.ERROR_NAME_IS_NULL;
import static edu.homework.hw4.ErrorMessages.ERROR_WEIGHT_IS_HUGE;
import static edu.homework.hw4.ErrorMessages.ERROR_WEIGHT_IS_NEGATIVE;
import static edu.homework.hw4.ErrorsFields.FIELD_AGE;
import static edu.homework.hw4.ErrorsFields.FIELD_HEIGHT;
import static edu.homework.hw4.ErrorsFields.FIELD_NAME;
import static edu.homework.hw4.ErrorsFields.FIELD_WEIGHT;

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


        return list.stream().collect(Collectors.toMap(Animal::name,
                animal -> validateAnimal(animal, false)
        ));
    }

    public Map<String, String> validateTask20(List<Animal> list) {

        AnimalUtilities.nullChecker(list);


        return list.stream().collect(
                Collectors.toMap(Animal::name, animal -> {
                    Set<ValidationError> errors = validateAnimal(animal, true);
                    return errors.stream().map(ValidationError::getMessage).collect(Collectors.joining(", "));
                })
        );
    }

    private Set<ValidationError> validateAnimal(Animal animal, boolean isDetailed) {
        Set<ValidationError> errors = new LinkedHashSet<>();

        if (animal.name() == null) {
            errors.add(error(ERROR_NAME_IS_NULL, FIELD_NAME, isDetailed));
            return errors;
        }

        if (animal.name().isBlank()) {
            errors.add(error(ERROR_NAME_IS_BLANK, FIELD_NAME, isDetailed));
        }

        Matcher matcher = Pattern.compile(NAME_PATTERN).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(error(ERROR_NAME_IS_INVALID, FIELD_NAME, isDetailed));
        }

        if (animal.age() < MIN_AGE) {
            errors.add(error(ERROR_AGE_IS_NEGATIVE, FIELD_AGE, isDetailed));
        }
        if (animal.age() > MAX_AGE) {
            errors.add(error(ERROR_AGE_IS_HUGE, FIELD_AGE, isDetailed));
        }

        if (animal.height() < MIN_HEIGHT) {
            errors.add(error(ERROR_HEIGHT_IS_NEGATIVE, FIELD_HEIGHT, isDetailed));
        }
        if (animal.height() > MAX_HEIGHT) {
            errors.add(error(ERROR_HEIGHT_IS_HUGE, FIELD_HEIGHT, isDetailed));
        }

        if (animal.weight() < MIN_WEIGHT) {
            errors.add(error(ERROR_WEIGHT_IS_NEGATIVE, FIELD_WEIGHT, isDetailed));
        }
        if (animal.weight() > MAX_WEIGHT) {
            errors.add(error(ERROR_WEIGHT_IS_HUGE, FIELD_WEIGHT, isDetailed));
        }

        return errors;
    }

    private ValidationError error(ErrorMessages messages, ErrorsFields errorsFields, boolean detailed) {
        if (detailed) {
            return new ValidationError(messages, errorsFields);
        } else {
            return new ValidationError(messages);
        }
    }


}
