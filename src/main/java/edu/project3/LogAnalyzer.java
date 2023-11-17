package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {

    public static void main(String[] args) {

        Arrays.stream(args).forEach(System.out::println);




        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        String logPath = args[1];
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        String outputFormat = "markdown";


        for (int i = 2; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path":
                    logPath = args[i + 1];
                    break;
                case "--from":

                    fromDate = parseISO8601Date(args[i + 1]);

                    break;
                case "--to":
                    toDate = LocalDateTime.parse(args[i + 1], DateTimeFormatter.ISO_DATE_TIME);
                    break;
                case "--format":
                    outputFormat = args[i + 1];
                    break;
                default:
                    System.out.println("Invalid option: " + args[i]);
                    return;
            }
        }

        try {

            List<LogRecord> logRecords = readAndParseLogs(logPath);
            LogReport logReport = analyzeLogs(logRecords, fromDate, toDate, logPath);


            switch (outputFormat) {
                case "markdown":
                    System.out.println(logReport.toMarkdown());
                    break;
                case "adoc":
                    System.out.println(logReport.toAsciiDoc());
                    break;
                default:
                    System.out.println("Invalid format: " + outputFormat);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LocalDateTime parseISO8601Date(String dateString) throws DateTimeParseException {
        return  LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();

//         LocalDateTime.parse(dateString, formatter);
    }

    private static List<LogRecord> readAndParseLogs(String logPath) throws IOException {
        if (logPath.startsWith("http")) {

            return null;


        } else {

            Path logFilePath = Path.of(logPath);
            return Files.lines(logFilePath).map(LogRecord::parse).toList();
        }
    }

    private static LogReport analyzeLogs(List<LogRecord> logRecords, LocalDateTime fromDate, LocalDateTime toDate, String logPath) {

        List<LogRecord> filteredLogs = filterLogsByDate(logRecords, fromDate, toDate);
        long totalRequests = countTotalRequests(filteredLogs);
        Map<String, Long> resourceCounts = countResourceRequests(filteredLogs);
        Map<Integer, Long> responseCodeCounts = countResponseCodes(filteredLogs);
        double avgResponseSize = calculateAverageResponseSize(filteredLogs);

        return new LogReport(logPath, fromDate, toDate, totalRequests, avgResponseSize, resourceCounts, responseCodeCounts);
    }
    private static List<LogRecord> filterLogsByDate(List<LogRecord> logRecords, LocalDateTime fromDate) {

        return filterLogsByDate(logRecords,fromDate,LocalDateTime.now());
    }
    private static List<LogRecord> filterLogsByDate(List<LogRecord> logRecords, LocalDateTime fromDate, LocalDateTime toDate) {
        if(toDate == null){
            toDate = LocalDateTime.now();
        }
        List<LogRecord> filteredLogs = new ArrayList<>();

        for (LogRecord log : logRecords) {
            if (log.getDateTime().isAfter(fromDate) && log.getDateTime().isBefore(toDate)) {
                filteredLogs.add(log);
            }
        }

        return filteredLogs;
//        return logRecords.stream().filter(log -> log.getDateTime().isAfter(fromDate))
//                .collect(Collectors.toList());
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