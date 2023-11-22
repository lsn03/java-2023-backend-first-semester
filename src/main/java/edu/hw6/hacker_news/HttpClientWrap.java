package edu.hw6.hacker_news;

import java.net.http.HttpResponse;

public interface HttpClientWrap {
    HttpResponse<String> makeResponse(String url);
}
