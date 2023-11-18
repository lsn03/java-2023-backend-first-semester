package edu.project3.parser;

import edu.project3.StatusCode;
import edu.project3.log.LogRecord;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class LogFileParser {

    private LogFileParser() {

    }

    public static List<LogRecord> parseLogs(String path) {

        Objects.requireNonNull(path);

        if (path.startsWith("http")) {
            return parseLogsFromHttp(path);
        } else if (path.contains("*")) {

            Path allPath = Paths.get(path);

            String directory = allPath.getParent().toString();

            String pattern = allPath.getFileName().toString();

            return parseLogsByTemplate(directory, pattern);
        } else {
            try {
                return Files.lines(Paths.get(path))
                        .map(LogRecord::parse)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    private static List<LogRecord> parseLogsByTemplate(String directory, String pattern) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);


        List<LogRecord> result = new ArrayList<>();
        try {
            Files.walk(Paths.get(directory))
                    .filter(path -> pathMatcher.matches(path.getFileName()))
                    .forEach(path -> {
                        try {
                            Files.lines(path).map(LogRecord::parse).forEach(result::add);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static List<LogRecord> parseLogsFromHttp(String path) {

        var response = makeResponse(path);
        if (response.statusCode() == StatusCode.SUCCESS_STATUS_CODE) {
            var body = response.body();
            var splitBody = body.split("\n");
            return Arrays.stream(splitBody).map(LogRecord::parse).toList();
        }
        return null;
    }

    private static HttpResponse<String> makeResponse(String url) {
        HttpClient client;
        try {
            client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
