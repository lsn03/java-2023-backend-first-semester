package edu.hw6;

import edu.hw6.hacker_news.HackerNews;
import edu.hw6.hacker_news.HttpClientWrap;
import edu.hw6.hacker_news.MockHttpClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import java.net.http.HttpResponse;

public class HackerNewsTest {


    //    private static final HackerNews HACKER_NEWS = new HackerNews(httpClient);
    HttpClientWrap httpClient;
    HackerNews HACKER_NEWS;

    @ParameterizedTest
    @CsvSource(value = {
            "38282166, Blender 16yo winner of UK young animator of the year",
            "38278753, Show HN: Outsmart AI in a Spy Game",
            "38268803, Breakthrough Thermoacoustic Stirling Generator Converts Energy No Moving Parts"
    })
    public void giveById(long id, String expected) {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("\"title\":\"" + expected + "\"");

        httpClient = new MockHttpClient(mockResponse);
        HACKER_NEWS = new HackerNews(httpClient);

        String news = HACKER_NEWS.news(id);
        assertEquals(expected, news);
    }

    @Test
    public void topStoriesTest() {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("[1, 2, 3]");

        httpClient = new MockHttpClient(mockResponse);
        HACKER_NEWS = new HackerNews(httpClient);


        long[] arr = HACKER_NEWS.hackerNewsTopStories();
        assertTrue(arr.length > 0);
    }

    @Test
    public void getBadNews() {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("[]");

        httpClient = new MockHttpClient(mockResponse);
        HACKER_NEWS = new HackerNews(httpClient);


        assertNull(HACKER_NEWS.news(-1));

    }
}
