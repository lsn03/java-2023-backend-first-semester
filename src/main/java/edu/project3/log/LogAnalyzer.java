package edu.project3.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class LogAnalyzer {
    private static LocalDateTime newFromDate;
    private static LocalDateTime newToDate;
    private final static int YEAR_OF_START = 1970;

    private LogAnalyzer() {

    }

    public static LogReport analyzeLogs(
            List<LogRecord> logRecords,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String logPath
    ) {
        validateInput(logRecords, fromDate, toDate, logPath);


        List<LogRecord> filteredLogs = filterLogsByDate(logRecords, newFromDate, newToDate);
        LogStatistics logStatistics = new LogStatistics();

        long totalRequests = countTotalRequests(filteredLogs);
        Map<String, Long> resourceCounts = countResourceRequests(filteredLogs);
        Map<Integer, Long> responseCodeCounts = countResponseCodes(filteredLogs);
        double avgResponseSize = calculateAverageResponseSize(filteredLogs);
        Map<String, Long> uniqueIpCounts = uniqueIpCount(filteredLogs);
        Map<String, Long> httpUserAgentCount = httpUserAgentCount(filteredLogs);

        logStatistics.setTotalRequests(totalRequests);
        logStatistics.setResourceCounts(resourceCounts);
        logStatistics.setResponseCodeCount(responseCodeCounts);
        logStatistics.setAvgResponseSize(avgResponseSize);

        logStatistics.setUniqueIpCounts(uniqueIpCounts);
        logStatistics.setHttpUserAgentCount(httpUserAgentCount);

        return new LogReport(
                logPath,
                newFromDate,
                newToDate,
                logStatistics);
    }


    private static void validateInput(List<LogRecord> logRecords,
                                      LocalDateTime fromDate,
                                      LocalDateTime toDate,
                                      String logPath) {

        Objects.requireNonNull(logRecords);
        Objects.requireNonNull(logPath);
        newFromDate = Objects.requireNonNullElseGet(fromDate, () -> LocalDateTime.of(YEAR_OF_START, 1, 1, 0, 0));
        newToDate = Objects.requireNonNullElseGet(toDate, LocalDateTime::now);
    }


    private static List<LogRecord> filterLogsByDate(
            List<LogRecord> logRecords,
            LocalDateTime fromDate,
            LocalDateTime toDate) {

        List<LogRecord> filteredLogs = new ArrayList<>();

        for (LogRecord log : logRecords) {
            if (log.getDateTime().isAfter(fromDate) && log.getDateTime().isBefore(toDate)) {
                filteredLogs.add(log);
            }
        }

        return filteredLogs;

    }

    private static long countTotalRequests(List<LogRecord> logRecords) {
        return logRecords.size();
    }

    private static Map<String, Long> countResourceRequests(List<LogRecord> logRecords) {

        return logRecords.stream().collect(Collectors.groupingBy(LogRecord::getResource, Collectors.counting()));
    }

    private static Map<Integer, Long> countResponseCodes(List<LogRecord> logRecords) {

        return logRecords.stream().collect(Collectors.groupingBy(LogRecord::getResponseCode, Collectors.counting()));
    }

    private static double calculateAverageResponseSize(List<LogRecord> logRecords) {

        return logRecords.stream().mapToLong(LogRecord::getResponseSize).average().orElse(0);
    }

    private static Map<String, Long> httpUserAgentCount(List<LogRecord> filteredLogs) {
        return filteredLogs.stream().collect(Collectors.groupingBy(LogRecord::getHttpUserAgent, Collectors.counting()));
    }

    private static Map<String, Long> uniqueIpCount(List<LogRecord> filteredLogs) {

        return filteredLogs.stream().collect(Collectors.groupingBy(LogRecord::getRemoteAddr, Collectors.counting()));
    }
}
