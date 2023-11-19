package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    public static final String TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public static final String TEMPLATE_STORY = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    public static final int SUCCESS_RESPONSE_CODE = 200;
    public static final int NOT_FOUND_RESPONSE_CODE = 404;

    private HttpResponse<String> makeResponse(String url) {
        HttpClient client;
        try {
            client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public long[] hackerNewsTopStories() {
        var response = makeResponse(TOP_STORIES);

        if (response.statusCode() == SUCCESS_RESPONSE_CODE) {
            String[] idStrings = response.body().replaceAll("[\\[\\]\"]", "").split(",");
            long[] ids = new long[idStrings.length];
            for (int i = 0; i < idStrings.length; i++) {
                ids[i] = Long.parseLong(idStrings[i].trim());
            }
            return ids;
        }

        return null;
    }

    public String news(long id) {
        var response = makeResponse(String.format(TEMPLATE_STORY, id));

        if (response.statusCode() == SUCCESS_RESPONSE_CODE) {
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

}
