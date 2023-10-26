package edu.hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AnimalUtilitiesTest {
    @Test
    public void testThatHeightSortedCorrectTask1() {
//        Arrange
        List<Animal> list = List.of(new Animal("Zyblik", Animal.Type.BIRD, Animal.Sex.M, 10, 154, 25, true),
                new Animal("Fantik", Animal.Type.CAT, Animal.Sex.F, 10, 90, 25, true),
                new Animal("Gosha", Animal.Type.SPIDER, Animal.Sex.M, 10, 120, 25, true)
        );
//        Act
        list = AnimalUtilities.sortHeightTask1(list);
//        Assert
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).height() < list.get(i + 1).height());
        }
    }

    @Test
    public void testThatWeightSortedCorrectTask2() {
//        Arrange
        List<Animal> list = List.of(new Animal("Zyblik", Animal.Type.BIRD, Animal.Sex.M, 10, 154, 25, true),
                new Animal("Fantik", Animal.Type.CAT, Animal.Sex.F, 15, 90, 15, true),
                new Animal("Gosha", Animal.Type.SPIDER, Animal.Sex.M, 3, 120, 19, true),
                new Animal("Petya", Animal.Type.SPIDER, Animal.Sex.M, 4, 120, 45, true),
                new Animal("Maksim", Animal.Type.DOG, Animal.Sex.M, 1, 120, 35, true),
                new Animal("Bebra", Animal.Type.FISH, Animal.Sex.M, 2, 120, 105, true)
        );
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


}
