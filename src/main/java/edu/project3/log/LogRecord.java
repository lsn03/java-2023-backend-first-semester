package edu.project3.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecord {

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$");

    public static final int GROUP_REMOTE_ADDRESS = 1;
    public static final int GROUP_REMOTE_USER = 2;
    public static final int GROUP_LOCAL_DATE_TIME = 4;
    public static final int GROUP_REQUEST = 5;
    public static final int GROUP_STATUS = 6;
    public static final int GROUP_BODY_BYTES_SENT = 7;
    public static final int GROUP_HTTP_REFERER = 8;
    public static final int GROUP_HTTP_USER_AGENT = 9;

    private String remoteAddr;
    private String remoteUser;
    private LocalDateTime timeLocal;
    private String request;
    private int status;
    private long bodyBytesSent;
    private String httpReferer;
    private String httpUserAgent;

    @SuppressWarnings("ParameterNumber")
    public LogRecord(String remoteAddress,
                     String remoteUser,
                     LocalDateTime timeLocal,
                     String request,
                     int status,
                     long bodyBytesSent,
                     String httpReferer,
                     String httpUserAgent) {
        this.remoteAddr = remoteAddress;
        this.remoteUser = remoteUser;
        this.timeLocal = timeLocal;
        this.request = request;
        this.status = status;
        this.bodyBytesSent = bodyBytesSent;
        this.httpReferer = httpReferer;
        this.httpUserAgent = httpUserAgent;
    }

    public static LogRecord parse(String logLine) {
        Objects.requireNonNull(logLine);

        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.matches()) {

            return new LogRecord(
                    matcher.group(GROUP_REMOTE_ADDRESS),
                    matcher.group(GROUP_REMOTE_USER),
                    parseLocalDateTime(matcher.group(GROUP_LOCAL_DATE_TIME)),
                    matcher.group(GROUP_REQUEST),
                    Integer.parseInt(matcher.group(GROUP_STATUS)),
                    Long.parseLong(matcher.group(GROUP_BODY_BYTES_SENT)),
                    matcher.group(GROUP_HTTP_REFERER),
                    matcher.group(GROUP_HTTP_USER_AGENT)
            );

        } else {
            throw new IllegalArgumentException("Invalid log line format: " + logLine);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogRecord logRecord = (LogRecord) o;
        return status == logRecord.status
                && bodyBytesSent == logRecord.bodyBytesSent
                && Objects.equals(remoteAddr, logRecord.remoteAddr)
                && Objects.equals(remoteUser, logRecord.remoteUser)
                && Objects.equals(timeLocal, logRecord.timeLocal)
                && Objects.equals(request, logRecord.request)
                && Objects.equals(httpReferer, logRecord.httpReferer)
                && Objects.equals(httpUserAgent, logRecord.httpUserAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteAddr, remoteUser, timeLocal, request, status, bodyBytesSent, httpReferer, httpUserAgent);
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
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

    @Override
    public String toString() {
        return "LogRecord{"
                + "remoteAddr='" + remoteAddr + '\''
                + ", remoteUser='" + remoteUser + '\''
                + ", timeLocal=" + timeLocal
                + ", request='" + request + '\''
                + ", status=" + status
                + ", bodyBytesSent=" + bodyBytesSent
                + ", httpReferer='" + httpReferer + '\''
                + ", httpUserAgent='" + httpUserAgent + '\''
                + '}';
    }
    private static LocalDateTime parseLocalDateTime(String timeLocal) {

        return LocalDateTime.parse(
                timeLocal, DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH));
    }

}
