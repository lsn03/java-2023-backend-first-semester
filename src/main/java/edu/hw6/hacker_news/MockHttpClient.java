package edu.hw6.hacker_news;

import java.net.http.HttpResponse;

public class MockHttpClient implements HttpClientWrap {
    private final HttpResponse<String> mockResponse;

    public MockHttpClient(HttpResponse<String> mockResponse) {
        this.mockResponse = mockResponse;
    }

    @Override
    public HttpResponse<String> makeResponse(String url) {
        return mockResponse;
    }
}
