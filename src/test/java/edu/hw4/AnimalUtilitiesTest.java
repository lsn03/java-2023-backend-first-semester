package edu.hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimalUtilitiesTest {
    private List<Animal> list;
    private final Animal birdZyblik = new Animal("Zyblik", Animal.Type.BIRD, Animal.Sex.M, 10, 154, 25, true);
    private final Animal catFantik = new Animal("Fantik", Animal.Type.CAT, Animal.Sex.F, 15, 90, 15, true);
    private final Animal spiderGosha = new Animal("Gosha", Animal.Type.SPIDER, Animal.Sex.M, 3, 100, 19, true);
    private final Animal spiderPetya = new Animal("Petya", Animal.Type.SPIDER, Animal.Sex.F, 8, 80, 45, true);
    private final Animal dogMaksim = new Animal("Maksim", Animal.Type.DOG, Animal.Sex.M, 5, 130, 27, true);
    private final Animal fishBebra = new Animal("Bebra", Animal.Type.FISH, Animal.Sex.F, 2, 120, 125, false);

    @BeforeEach
    public void initializer() {
        list = new ArrayList<>(Arrays.asList(birdZyblik, catFantik, spiderGosha, spiderPetya, dogMaksim, fishBebra));
    }

    @Test
    public void heightSortedCorrectTask1() {
//        Arrange

//        Act
        list = AnimalUtilities.sortHeightTask1(list);
//        Assert
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).height() <= list.get(i + 1).height());
        }
    }

    @Test
    public void weightSortedCorrectTask2() {
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
    public void typeCounterIsCorrect() {
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
    public void maxNameLenghtCorrect() {
//        Arrange
        Animal expectedAnimal = list.get(0);
//        Act
        Animal res = AnimalUtilities.getTheLongestNameTask4(list);
//        Assert
        assertEquals(expectedAnimal, res);
    }

    @Test
    public void sexMCounterIsCorrect() {
//        Arrange
        Animal.Sex sexExpected = Animal.Sex.M;
//        Act
        Animal.Sex sexRes = AnimalUtilities.getTheMostPopularSexTask5(list);
//        Assert
        assertEquals(sexExpected, sexRes);
    }

    @Test
    public void sexFCounterIsCorrect() {
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
    public void heightAndTypeIsCorrect() {
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
    public void theOldestAnimalIsCorrect() {
//        Arrange
        Animal animalExpected = birdZyblik;
//        Act
        var animalRes = AnimalUtilities.getTheKTheMostOldAnimalTask7(list, 1);
//        Assert
        assertEquals(animalExpected, animalRes);

    }

    @Test
    public void heaviestAnimalFound() {
//        Arrange
        Optional<Animal> animalExpected = Optional.ofNullable(spiderPetya);
//        Act
        var animalRes = AnimalUtilities.getTheMostWeightAnimalLessNHeightTask8(list, 100);
//        Assert
        assertEquals(animalExpected, animalRes);

    }

    @Test
    public void heaviestAnimalNotFound() {
//        Arrange

//        Act
        var animalRes = AnimalUtilities.getTheMostWeightAnimalLessNHeightTask8(list, 10);
//        Assert
        assertFalse(animalRes.isPresent());

    }

    @Test
    public void countingCorrect() {
//        Arrange
        Integer expectedResult = 26;
//        Act
        var animalRes = AnimalUtilities.getTheCountOFPawsTask9(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void countingNotCorrect() {
//        Arrange
        list.clear();
        list = null;
//        Act
        assertThrows(NullPointerException.class, () -> {
            AnimalUtilities.getTheCountOFPawsTask9(list);
        });

        assertThrows(NullPointerException.class, () -> {
            list = new ArrayList<>(Arrays.asList((Animal) null));

            AnimalUtilities.getTheCountOFPawsTask9(list);
        });


    }

    @Test
    public void pawsCorrect() {
//        Arrange
        List<Animal> expectedResult = new ArrayList<>(list);
        expectedResult.remove(spiderPetya);
//        Act
        var animalRes = AnimalUtilities.getAnimalWithAgeNotEqualPawsTask10(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void canBiteAndToldCorrect() {
//        Arrange
        List<Animal> expectedResult = new ArrayList<>();
        expectedResult.add(birdZyblik);
        expectedResult.add(dogMaksim);
//        Act
        var animalRes = AnimalUtilities.getAnimalsWhoCanBiteAndToldTask11(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void countOfAnimalsAtTask12Correct() {
//        Arrange
        Integer expectedResult = 1;

//        Act
        var animalRes = AnimalUtilities.getCountOfAnimalWhereWeightMoreThanHeightTask12(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void nameAnimalsMoreThan2WordCorrectTest1() {
//        Arrange
        List<Animal> expectedResult = List.of();

//        Act
        var animalRes = AnimalUtilities.getAnimalsWithNameMoreThan2WordTask13(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void isDogWithHeightExist() {
//        Arrange
//        Act
        var animalRes = AnimalUtilities.isExistDogWithKHeightTask14(list, 100);
//        Assert
        assertTrue(animalRes);

    }

    @Test
    public void isDogWithHeightNotExist() {
//        Arrange
//        Act
        var animalRes = AnimalUtilities.isExistDogWithKHeightTask14(list, 200);
//        Assert
        assertFalse(animalRes);

    }

    @Test
    public void summingWeightAnimalsWithRangeByAge() {
//        Arrange
        Integer expectedResult = 72;
        Range range = new Range(5, 9);
//        Act
        var animalRes = AnimalUtilities.getAnimalWeightWithRangeOfAgeTask15(list, range);
//        Assert
        assertEquals(expectedResult, animalRes);

    }


    @Test
    public void sortedByTypeSexName() {
        Animal newCatGantik = new Animal("Gantik", Animal.Type.CAT, Animal.Sex.F, 15, 90, 15, true);

        Animal newDogAloha = new Animal("Aloha", Animal.Type.DOG, Animal.Sex.M, 15, 90, 15, true);

//        Arrange
        var expectedResult = Arrays.asList(
                catFantik,
                newCatGantik,
                newDogAloha,
                dogMaksim,
                birdZyblik,
                fishBebra,
                spiderGosha,
                spiderPetya
        );

        list.add(newCatGantik);
        list.add(newDogAloha);
//        Act
        var animalRes = AnimalUtilities.sortedByTypeThenSexThenNameTask16(list);
//        Assert
        assertEquals(expectedResult, animalRes);

    }


    @Test
    public void spidersBitesMoreThanDogCorrect() {
//        Arrange

//        Act
        var animalRes = AnimalUtilities.isSpiderBitesMoreThanDogsTask17(list);
//        Assert
        assertTrue(animalRes);

    }

    @Test
    public void spidersBitesMoreThanDogCorrect2() {
//        Arrange
        list.remove(spiderPetya);
//        Act
        var animalRes = AnimalUtilities.isSpiderBitesMoreThanDogsTask17(list);
//        Assert
        assertFalse(animalRes);

    }

    @Test
    public void spidersBitesMoreThanDogCorrect3() {
//        Arrange
        list.remove(spiderPetya);

        list.add(new Animal("dog12", Animal.Type.DOG, Animal.Sex.F, 10, 10, 10, true));
//        Act
        var animalRes = AnimalUtilities.isSpiderBitesMoreThanDogsTask17(list);
//        Assert
        assertFalse(animalRes);

    }

    @Test
    public void fishWasFound() {


        Animal newFishGantik = new Animal("Gantik", Animal.Type.FISH, Animal.Sex.F, 15, 90, 150, true);

        Animal newDogAloha = new Animal("Aloha", Animal.Type.DOG, Animal.Sex.M, 15, 90, 15, true);

//        Arrange
        var secondListToAdd = Arrays.asList(
                catFantik,
                newFishGantik,
                newDogAloha,
                dogMaksim,
                birdZyblik,
                fishBebra,
                spiderGosha,
                spiderPetya
        );

        List<List<Animal>> sourceList = new ArrayList<>(Arrays.asList(list, secondListToAdd));

        var expectedResult = newFishGantik;
//        Act
        var animalRes = AnimalUtilities.getTheMostWeightFishTask18(sourceList);
//        Assert
        assertEquals(expectedResult, animalRes);

    }

    @Test
    public void fishWasNotFound() {


        Animal newDogGantik = new Animal("Gantik", Animal.Type.DOG, Animal.Sex.F, 15, 90, 150, true);

        Animal newDogAloha = new Animal("Aloha", Animal.Type.DOG, Animal.Sex.M, 15, 90, 15, true);

//        Arrange
        var secondListToAdd = Arrays.asList(

                newDogGantik,
                newDogAloha

        );
        list.remove(fishBebra);
        List<List<Animal>> sourceList = new ArrayList<>(Arrays.asList(list, secondListToAdd));


//        Act
        var animalRes = AnimalUtilities.getTheMostWeightFishTask18(sourceList);
//        Assert
        assertNull(animalRes);

    }
}
