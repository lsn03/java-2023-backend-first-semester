package edu.hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class AnimalValidatorTask20Test {

    static AnimalValidator animalValidator;
    List<Animal> list;


    String name;
    Animal.Type type;
    Animal.Sex sex;
    int age;
    int height;
    int weight;
    boolean bites = true;

    @BeforeAll
    public static void validatorInitializer() {
        animalValidator = new AnimalValidator();
    }

    @BeforeEach
    public void initializer() {
        list = new ArrayList<>();


        name = "Zyablick";
        type = Animal.Type.BIRD;
        sex = Animal.Sex.M;
        age = 10;
        height = 154;
        weight = 25;
        bites = true;

    }

    @Test
    public void testTask19NameInvalid() {
        name = "Zyblik2";
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_NAME_IS_INVALID, Errors.FIELD_NAME);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20NameBlank() {
        name = " ";
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_NAME_IS_BLANK, Errors.FIELD_NAME);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20NameNull() {
        name = null;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_NAME_IS_NULL, Errors.FIELD_NAME);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20AgeNegative() {
        age = -10;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_AGE_IS_NEGATIVE, Errors.FIELD_AGE);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20AgeHuge() {
        age = 200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_AGE_IS_HUGE, Errors.FIELD_AGE);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20HeightNegative() {
        height = -200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_HEIGHT_IS_NEGATIVE, Errors.FIELD_HEIGHT);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20HeightHuge() {
        height = 2000;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_HEIGHT_IS_HUGE, Errors.FIELD_HEIGHT);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20WeightNegative() {
        weight = -200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_WEIGHT_IS_NEGATIVE, Errors.FIELD_WEIGHT);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20WeightHuge() {
        weight = 2000;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_WEIGHT_IS_HUGE, Errors.FIELD_WEIGHT);


        assertEquals(error.getMessage(), result.get(name));

    }

    @Test
    public void testTask20WeightHugeNameBlankAgeNegative() {
        weight = 2000;
        name = " ";
        age = -1;

        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask20(list);


        ValidationError error = new ValidationError(Errors.ERROR_WEIGHT_IS_HUGE, Errors.FIELD_WEIGHT);
        ValidationError error2 = new ValidationError(Errors.ERROR_NAME_IS_BLANK, Errors.FIELD_NAME);
        ValidationError error3 = new ValidationError(Errors.ERROR_AGE_IS_NEGATIVE, Errors.FIELD_AGE);

        String str = error2.getMessage() + ", " + error3.getMessage() + ", " + error.getMessage();

        assertEquals(str, result.get(name));

    }


    /*



     */


}
