package edu.hw3;

import edu.homework.hw3.NullComparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class NullComporatorTest {
    private TreeMap<String, String> tree;

    @BeforeEach
    public void initTree() {
        tree = new TreeMap<>(new NullComparator<>());
        tree.put("1", "10");
        tree.put("3", "30");
        tree.put(null, "test");
        tree.put("2", "20");
    }

    @Test
    public void testThatNullCorrect() {

//        Assert
        assertTrue(tree.containsKey(null));

    }

    @Test
    public void testMapEquals() {
//        Arrange

        TreeMap<String, String> excepted = new TreeMap<>(new NullComparator<>());
        excepted.put(null, "test");
        excepted.put("2", "20");
        excepted.put("1", "10");
        excepted.put("3", "30");
//        Act


//        Assert
        assertEquals(excepted, tree);

    }

    @Test
    public void testIterator() {
//        Arrange

        TreeMap<String, String> excepted = new TreeMap<>(new NullComparator<>());

        excepted.put(null, "test");
        excepted.put("2", "20");
        excepted.put("1", "10");
        excepted.put("3", "30");
//        Act

        Iterator<Map.Entry<String, String>> iterator = tree.entrySet().iterator();

        while (iterator.hasNext()) {
            var entry = iterator.next();
            var key = entry.getKey();
            var value = entry.getValue();

            assertEquals(excepted.get(key), value);
        }

    }

    @Test
    public void testWithoutComparator() {

        TreeMap<String, String> excepted = new TreeMap<>();

        assertThrows(NullPointerException.class, () -> {
            excepted.put(null, "test");
        });

    }
}
