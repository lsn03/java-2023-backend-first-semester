package edu.hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AnimalUtilitiesTest {
    @Test
    public void testThatHeightSortedCorrect() {
//        Arrange
        List<Animal> list = List.of(new Animal("a", Animal.Type.BIRD, Animal.Sex.F, 10, 100, 25, true),
                new Animal("a", Animal.Type.CAT, Animal.Sex.F, 10, 90, 25, true),
                new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 10, 120, 25, true)
        );
//        Act
        list = AnimalUtilities.sortHeight(list);
//        Assert
        for (int i = 0; i < list.size()-1; i++) {
            assertTrue(list.get(i).height()  <list.get(i+1).height() );
        }

    }
}
