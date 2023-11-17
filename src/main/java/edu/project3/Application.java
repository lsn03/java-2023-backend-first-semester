package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Application {

    private static String logPath;
    private static LocalDateTime fromDate;
    private static LocalDateTime toDate;
    private static String outputFormat;
    private static List<LogRecord> logRecords;
    private static LogReport logReport;

    private final static int YEAR_OF_START = 1970;
    private final static int UNREACHEABLE_YEAR = 10000;

    private Application() {

    }

    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        parseDataFromArgs(args);

        logRecords = LogFileParser.parseLogs(logPath);
        logReport = LogAnalyzer.analyzeLogs(logRecords, fromDate, toDate, logPath);

        print(logReport);

    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void print(LogReport logReport) {

        switch (outputFormat) {
            case "markdown" -> System.out.println(logReport.toMarkdown());
            case "adoc" -> System.out.println(logReport.toAsciiDoc());
            default -> throw new IllegalArgumentException();
        }
    }

    private static void parseDataFromArgs(String[] args) {
        String commandLine = String.join(" ", args);

        Pattern pathPattern = Pattern.compile("--path\\s+(\\S+)");
        Pattern fromPattern = Pattern.compile("--from\\s+(\\S+)");
        Pattern toPattern = Pattern.compile("--to\\s+(\\S+)");
        Pattern formatPattern = Pattern.compile("--format\\s+(\\S+)");


        Matcher pathMatcher = pathPattern.matcher(commandLine);
        Matcher fromMatcher = fromPattern.matcher(commandLine);
        Matcher toMatcher = toPattern.matcher(commandLine);
        Matcher formatMatcher = formatPattern.matcher(commandLine);


        logPath = pathMatcher.find() ? pathMatcher.group(1) : null;
        if (fromMatcher.find()) {
            fromDate = parseISO8601Date(fromMatcher.group(1));
        } else {
            fromDate = LocalDateTime.of(YEAR_OF_START, 1, 1, 0, 0, 0);

        }
        if (toMatcher.find()) {
            toDate = parseISO8601Date(fromMatcher.group(1));
        } else {
            toDate = LocalDateTime.now().plusYears(UNREACHEABLE_YEAR);
        }

        outputFormat = formatMatcher.find() ? formatMatcher.group(1) : outputFormat;
    }

    private static LocalDateTime parseISO8601Date(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
    }
}
