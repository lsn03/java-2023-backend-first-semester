package edu.hw4;

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


        return list.stream().collect(Collectors.toMap(Animal::name,this::validateAnimalTask19));
    }
    public Map<String, String> validateTask20(List<Animal> list) {

        AnimalUtilities.nullChecker(list);


        return list.stream().collect(
                Collectors.toMap(Animal::name,animal -> {
                    Set<ValidationError> errors = validateAnimalTask20(animal);
                    return errors.stream().map(ValidationError::getMessage).collect(Collectors.joining(", "));
                })
        );
    }

    private Set<ValidationError> validateAnimalTask19(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name().isBlank()) {
            errors.add(new ValidationError("Имя не может быть пустым"));
        }
        if (animal.name() == null) {
            errors.add(new ValidationError("Имя не может быть null"));
        }
        String regExp = "^[A-Za-zА-Яа-я\\s]+$";
        Matcher matcher = Pattern.compile(regExp).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError("Имя должно содержать символы латинцы, кириллицы и пробелы: " + regExp));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("Возраст не может быть меньше 0"));
        }
        if (animal.age() > 20) {
            errors.add(new ValidationError("Возраст не может быть больше 20"));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError("Высота не может быть меньше 0"));
        }
        if (animal.height() > 200) {
            errors.add(new ValidationError("Высота не может быть больше 200"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Вес не может быть меньше 0"));
        }
        if (animal.weight() > 100) {
            errors.add(new ValidationError("Вес не может быть больше 100"));
        }

        return errors;
    }
    private Set<ValidationError> validateAnimalTask20(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if(animal==null){
            errors.add(new ValidationError("Животное не может быть null"));
            return errors;
        }

        if (animal.name().isBlank()) {
            errors.add(new ValidationError("Имя не может быть пустым","name"));
        }
        if (animal.name() == null) {
            errors.add(new ValidationError("Имя не может быть null","name"));
        }
        String regExp = "^[A-Za-zА-Яа-я\\s]+$";
        Matcher matcher = Pattern.compile(regExp).matcher(animal.name());

        if (!matcher.matches()) {
            errors.add(new ValidationError("Имя должно содержать символы латинцы, кириллицы и пробелы: " + regExp,"name"));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("Возраст не может быть меньше 0","age"));
        }
        if (animal.age() > 20) {
            errors.add(new ValidationError("Возраст не может быть больше 20","age"));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError("Высота не может быть меньше 0","height"));
        }
        if (animal.height() > 200) {
            errors.add(new ValidationError("Высота не может быть больше 200","height"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Вес не может быть меньше 0","weight"));
        }
        if (animal.weight() > 100) {
            errors.add(new ValidationError("Вес не может быть больше 100","weight"));
        }

        return errors;
    }

}
