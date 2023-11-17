package edu.hw6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HackerNewsTest {
    private static final HackerNews HACKER_NEWS = new HackerNews();

    @ParameterizedTest
    @CsvSource(value = {
            "38282166, Blender 16yo winner of UK young animator of the year",
            "38278753, Show HN: Outsmart AI in a Spy Game",
            "38268803, Breakthrough Thermoacoustic Stirling Generator Converts Energy No Moving Parts"
    })
    public void giveById(long id, String expected) {

        String news = HACKER_NEWS.news(id);
        assertEquals(expected, news);
    }

    @Test
    public void topStoriesTest() {
        long[] arr = HACKER_NEWS.hackerNewsTopStories();
        assertTrue(arr.length > 0);
    }

    @Test
    public void getBadNews() {
        assertNull(HACKER_NEWS.news(-1));

    }
}
