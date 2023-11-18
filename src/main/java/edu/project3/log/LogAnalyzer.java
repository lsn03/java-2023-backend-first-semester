package edu.project3.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LogAnalyzer {

    private LogAnalyzer() {

    }

    public static LogReport analyzeLogs(
            List<LogRecord> logRecords,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String logPath
    ) {

        List<LogRecord> filteredLogs = filterLogsByDate(logRecords, fromDate, toDate);
        long totalRequests = countTotalRequests(filteredLogs);
        Map<String, Long> resourceCounts = countResourceRequests(filteredLogs);
        Map<Integer, Long> responseCodeCounts = countResponseCodes(filteredLogs);
        double avgResponseSize = calculateAverageResponseSize(filteredLogs);

        return new LogReport(
                logPath,
                fromDate,
                toDate,
                totalRequests,
                avgResponseSize,
                resourceCounts,
                responseCodeCounts);
    }

    private static List<LogRecord> filterLogsByDate(List<LogRecord> logRecords, LocalDateTime fromDate) {
        return filterLogsByDate(logRecords, fromDate, LocalDateTime.now());
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
}
