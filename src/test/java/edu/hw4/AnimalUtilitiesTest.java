package edu.hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class AnimalUtilitiesTest {
    List<Animal> list;
    Animal birdZyblik = new Animal("Zyblik", Animal.Type.BIRD, Animal.Sex.M, 10, 154, 25, true);
    Animal catFantik = new Animal("Fantik", Animal.Type.CAT, Animal.Sex.F, 15, 90, 15, true);
    Animal spiderGosha = new Animal("Gosha", Animal.Type.SPIDER, Animal.Sex.M, 3, 100, 19, true);
    Animal spiderPetya = new Animal("Petya", Animal.Type.SPIDER, Animal.Sex.F, 4, 80, 45, true);
    Animal dogMaksim = new Animal("Maksim", Animal.Type.DOG, Animal.Sex.M, 5, 130, 27, true);
    Animal fishBebra = new Animal("Bebra", Animal.Type.FISH, Animal.Sex.F, 2, 120, 105, true);

    @BeforeEach
    public void initializer() {
        list = new ArrayList<>(Arrays.asList(
                birdZyblik,
                catFantik,
                spiderGosha,
                spiderPetya,
                dogMaksim,
                fishBebra
        ));
    }

    @Test
    public void testThatHeightSortedCorrectTask1() {
//        Arrange

//        Act
        list = AnimalUtilities.sortHeightTask1(list);
//        Assert
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).height() <= list.get(i + 1).height());
        }
    }

    @Test
    public void testThatWeightSortedCorrectTask2() {
//        Arrange

        int k1 = 2;
        int k2 = 3;
//        Act
        var list1 = AnimalUtilities.sortWeightTask2(list, k1);
        var list2 = AnimalUtilities.sortWeightTask2(list, k2);
//        Assert
        for (int i = 0; i < list1.size() - 1; i++) {
            assertTrue(list1.get(i).weight() >= list1.get(i + 1).weight());
        }
        for (int i = 0; i < list2.size() - 1; i++) {
            assertTrue(list2.get(i).weight() >= list2.get(i + 1).weight());
        }
    }

    @Test
    public void testThatTypeCounterIsCorrect() {
//        Arrange
        var mapExpected = new HashMap<Animal.Type, Integer>();

        mapExpected.put(Animal.Type.SPIDER, 2);
        mapExpected.put(Animal.Type.BIRD, 1);
        mapExpected.put(Animal.Type.CAT, 1);
        mapExpected.put(Animal.Type.DOG, 1);
        mapExpected.put(Animal.Type.FISH, 1);

//        Act
        var mapResult = AnimalUtilities.getCounterOfAnimalTypeTask3(list);

//        Assert
        assertEquals(mapExpected, mapResult);
    }

    @Test
    public void testThatMaxNameLenghtCorrect() {
//        Arrange
        Animal expectedAnimal = list.get(0);
//        Act
        Animal res = AnimalUtilities.getTheLongestNameTask4(list);
//        Assert
        assertEquals(expectedAnimal, res);
    }

    @Test
    public void testThatSexMCounterIsCorrectCorrect() {
//        Arrange
        Animal.Sex sexExpected = Animal.Sex.M;
//        Act
        Animal.Sex sexRes = AnimalUtilities.getTheMostPopularSexTask5(list);
//        Assert
        assertEquals(sexExpected, sexRes);
    }

    @Test
    public void testThatSexFCounterIsCorrectCorrect() {
//        Arrange
        Animal.Sex sexExpected = Animal.Sex.F;
        list.add(new Animal("ds", Animal.Type.BIRD, Animal.Sex.F, 5, 15, 30, false));
        list.add(new Animal("fdsad", Animal.Type.SPIDER, Animal.Sex.F, 4, 15, 20, false));
//        Act
        Animal.Sex sexRes = AnimalUtilities.getTheMostPopularSexTask5(list);
//        Assert
        assertEquals(sexExpected, sexRes);
    }

    @Test
    public void testThatHeightAndTypeIsCorrect() {
//        Arrange
        Map<Animal.Type, Animal> expectedMap = new HashMap<>();

        expectedMap.put(Animal.Type.DOG, dogMaksim);
        expectedMap.put(Animal.Type.CAT, catFantik);
        expectedMap.put(Animal.Type.BIRD, birdZyblik);
        expectedMap.put(Animal.Type.FISH, fishBebra);
        expectedMap.put(Animal.Type.SPIDER, spiderPetya);

//        Act
        var mapRes = AnimalUtilities.getTheWeightAnimalOfTypeTask6(list);
//        Assert
        assertEquals(expectedMap, mapRes);
    }

    @Test
    public void testThatKYheOldestAnimalIsCorrect() {
//        Arrange
        Animal animalExpected = birdZyblik;
//        Act
        var animalRes = AnimalUtilities.getTheKTheMostOldAnimalTask7(list, 1);
//        Assert
        assertEquals(animalExpected, animalRes);
        IdentityHashMap
    }
}
