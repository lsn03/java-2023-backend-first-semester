package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqDictionaryTest {
    @Test
    public void testThatStringWorkGood1() {
        FreqDictionary fd = new FreqDictionary();

        List<String> list = Arrays.asList("this", "that", "this", "ab", "beb", "this");

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("this", 3);
        expectedMap.put("that", 1);
        expectedMap.put("ab", 1);
        expectedMap.put("beb", 1);

        Map<String, Integer> map = fd.freqDict(list);

        assertEquals(expectedMap, map);

    }

    @Test
    public void testThatStringWorkGood2() {
        FreqDictionary fd = new FreqDictionary();

        List<String> list = Arrays.asList("код", "код", "код", "bug");
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("код", 3);
        expectedMap.put("bug", 1);


        Map<String, Integer> map = fd.freqDict(list);


        assertEquals(expectedMap, map);

    }

    @Test
    public void testThatIntegerWorkGood1() {
        FreqDictionary fd = new FreqDictionary();

        List<Integer> list = Arrays.asList(1, 1, 2, 2);
        Map<Integer, Integer> expectedMap = new HashMap<>();
        expectedMap.put(1, 2);
        expectedMap.put(2, 2);


        Map<Integer, Integer> map = fd.freqDict(list);


        assertEquals(expectedMap, map);

    }

    @Test
    public void testThatDoubleWorkGood1() {
        FreqDictionary fd = new FreqDictionary();

        List<Double> list = Arrays.asList(1.0, 1.0, 2.0, 3.0);
        Map<Double, Integer> expectedMap = new HashMap<>();
        expectedMap.put(1.0, 2);
        expectedMap.put(2.0, 1);
        expectedMap.put(3.0, 1);


        Map<Double, Integer> map = fd.freqDict(list);


        assertEquals(expectedMap, map);

    }

    @Test
    public void testThatNullListElements() {
        FreqDictionary fd = new FreqDictionary();

        List<Double> list = Arrays.asList(null, null);
        Map<Double, Integer> expectedMap = new HashMap<>();
        expectedMap.put(null, 2);


        Map<Double, Integer> map = fd.freqDict(list);


        assertEquals(expectedMap, map);

    }

    @Test
    public void testThatNullList() {
        FreqDictionary fd = new FreqDictionary();

        List<Double> list = null;

        assertThrows(NullPointerException.class, () -> {
            fd.freqDict(list);
        });


    }

    @Test
    public void testThatEmptyList() {
        FreqDictionary fd = new FreqDictionary();

        List<Double> list = List.of();

        Map<Double, Integer> map = fd.freqDict(list);
        assertEquals(map.size(), 0);


    }
}
