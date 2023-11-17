package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecord {

    private static final Pattern LOG_PATTERN = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$");

    private String remoteAddr;
    private String remoteUser;
    private LocalDateTime timeLocal;
    private String request;
    private int status;
    private long bodyBytesSent;
    private String httpReferer;
    private String httpUserAgent;

    public LogRecord(String remoteAddr, String remoteUser, LocalDateTime timeLocal, String request, int status, long bodyBytesSent, String httpReferer, String httpUserAgent) {
        this.remoteAddr = remoteAddr;
        this.remoteUser = remoteUser;
        this.timeLocal = timeLocal;
        this.request = request;
        this.status = status;
        this.bodyBytesSent = bodyBytesSent;
        this.httpReferer = httpReferer;
        this.httpUserAgent = httpUserAgent;
    }

    public static LogRecord parse(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.matches()) {

            return new LogRecord(
                    matcher.group(1),
                    matcher.group(2),
                    parseLocalDateTime(matcher.group(4)),
                    matcher.group(5),
                    Integer.parseInt(matcher.group(6)),
                    Long.parseLong(matcher.group(7)),
                    matcher.group(8),
                    matcher.group(9)
            );

        } else {
            throw new IllegalArgumentException("Invalid log line format: " + logLine);
        }
    }

    private static LocalDateTime parseLocalDateTime(String timeLocal) {
        return  LocalDateTime.parse(timeLocal, DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH));
    }


    public LocalDateTime getDateTime() {

        return timeLocal;
    }

    public String getResource() {

        String[] requestParts = request.split(" ");
        return requestParts.length > 1 ? requestParts[1] : "";
    }

    public int getResponseCode() {
        return status;
    }

    public long getResponseSize() {
        return bodyBytesSent;
    }
}
