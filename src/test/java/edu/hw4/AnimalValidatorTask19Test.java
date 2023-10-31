package edu.hw4;

import static edu.hw4.ErrorMessages.ERROR_AGE_IS_HUGE;
import static edu.hw4.ErrorMessages.ERROR_AGE_IS_NEGATIVE;
import static edu.hw4.ErrorMessages.ERROR_HEIGHT_IS_HUGE;
import static edu.hw4.ErrorMessages.ERROR_HEIGHT_IS_NEGATIVE;
import static edu.hw4.ErrorMessages.ERROR_NAME_IS_BLANK;
import static edu.hw4.ErrorMessages.ERROR_NAME_IS_INVALID;
import static edu.hw4.ErrorMessages.ERROR_NAME_IS_NULL;
import static edu.hw4.ErrorMessages.ERROR_WEIGHT_IS_HUGE;
import static edu.hw4.ErrorMessages.ERROR_WEIGHT_IS_NEGATIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalValidatorTask19Test {

    static AnimalValidator animalValidator;
    List<Animal> list;
    Set<ValidationError> set;

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
        set = new HashSet<>();

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

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_NAME_IS_INVALID);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19NameBlank() {
        name = " ";
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_NAME_IS_BLANK);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19NameNull() {
        name = null;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_NAME_IS_NULL);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19AgeNegative() {
        age = -10;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_AGE_IS_NEGATIVE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19AgeHuge() {
        age = 200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_AGE_IS_HUGE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19HeightNegative() {
        height = -200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_HEIGHT_IS_NEGATIVE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19HeightHuge() {
        height = 2000;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_HEIGHT_IS_HUGE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19WeightNegative() {
        weight = -200;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_WEIGHT_IS_NEGATIVE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19WeightHuge() {
        weight = 2000;
        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_WEIGHT_IS_HUGE);

        set.add(error);

        assertEquals(set, result.get(name));

    }

    @Test
    public void testTask19WeightHugeNameBlankAgeNegative() {
        weight = 2000;
        name = " ";
        age = -1;

        list.add(new Animal(name, type, sex, age, height, weight, bites));

        var result = animalValidator.validateTask19(list);


        ValidationError error = new ValidationError(ERROR_WEIGHT_IS_HUGE);
        ValidationError error2 = new ValidationError(ERROR_NAME_IS_BLANK);
        ValidationError error3 = new ValidationError(ERROR_AGE_IS_NEGATIVE);
        set.add(error);
        set.add(error2);
        set.add(error3);

        assertEquals(set, result.get(name));

    }


    /*



     */


}
