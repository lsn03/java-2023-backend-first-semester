package edu.hw4;

public final class Errors {

    private Errors() {

    }

    public static final String ERROR_NAME_IS_BLANK = "Имя не может быть пустым";
    public static final String ERROR_NAME_IS_NULL = "Имя не может быть null";
    public static final String ERROR_NAME_IS_INVALID = "Имя должно содержать символы латинцы, кириллицы и пробелы";
    public static final String ERROR_AGE_IS_NEGATIVE = "Возраст не может быть меньше 0";
    public static final String ERROR_AGE_IS_HUGE = "Возраст не может быть больше 20";
    public static final String ERROR_HEIGHT_IS_NEGATIVE = "Высота не может быть меньше 0";
    public static final String ERROR_HEIGHT_IS_HUGE = "Высота не может быть больше 200";
    public static final String ERROR_WEIGHT_IS_NEGATIVE = "Вес не может быть меньше 0";
    public static final String ERROR_WEIGHT_IS_HUGE = "Вес не может быть больше 100";

    public static final String FIELD_WEIGHT = "weight";
    public static final String FIELD_HEIGHT = "height";
    public static final String FIELD_AGE = "age";

    public static final String FIELD_NAME = "name";

}
