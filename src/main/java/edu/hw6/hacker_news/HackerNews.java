package edu.hw6.hacker_news;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String TEMPLATE_STORY = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final int SUCCESS_RESPONSE_CODE = 200;
    private final HttpClientWrap httpClient;

    public HackerNews(HttpClientWrap httpClient) {
        this.httpClient = httpClient;
    }

    public long[] hackerNewsTopStories() {
        var response = httpClient.makeResponse(TOP_STORIES);

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
        var response = httpClient.makeResponse(String.format(TEMPLATE_STORY, id));

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
