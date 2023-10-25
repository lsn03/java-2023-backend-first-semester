package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackwardIteratorTest {

    @Test
    public void testThatBakcwardWorkForInteger(){
        List<Integer> list = Arrays.asList(3,2,1);
        var backwardIterator = new BackwardIterator<Integer>(list);

        int index = list.size()-1 ;

        while (backwardIterator.hasNext()){
            Integer elementExpected = list.get(index--);
            Integer elementActual = backwardIterator.next();
            assertEquals(elementExpected,elementActual);
        }
    }

    @Test
    public void testThatBakcwardWorkForString(){
        List<String> list = Arrays.asList("ab","beb","kek","Lol");
        var backwardIterator = new BackwardIterator<String>(list);

        int index = list.size()-1 ;

        while (backwardIterator.hasNext()){
            var elementExpected = list.get(index--);
            var elementActual = backwardIterator.next();
            assertEquals(elementExpected,elementActual);
        }
    }
}
