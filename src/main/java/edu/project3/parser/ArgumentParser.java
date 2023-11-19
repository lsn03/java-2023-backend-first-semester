package edu.project3.parser;

import edu.project3.CommandLineArguments;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgumentParser {

    private final static int YEAR_OF_START = 1970;


    private ArgumentParser() {

    }

    public static CommandLineArguments parseDataFromArgs(String[] args) {
        String commandLine = String.join(" ", args);

        Pattern pathPattern = Pattern.compile("--path\\s+(\\S+)");
        Pattern fromPattern = Pattern.compile("--from\\s+(\\S+)");
        Pattern toPattern = Pattern.compile("--to\\s+(\\S+)");
        Pattern formatPattern = Pattern.compile("--format\\s+(\\S+)");


        Matcher pathMatcher = pathPattern.matcher(commandLine);
        Matcher fromMatcher = fromPattern.matcher(commandLine);
        Matcher toMatcher = toPattern.matcher(commandLine);
        Matcher formatMatcher = formatPattern.matcher(commandLine);


        String logPath = pathMatcher.find() ? pathMatcher.group(1) : null;
        LocalDateTime fromDate;
        if (fromMatcher.find()) {
            fromDate = parseISO8601Date(fromMatcher.group(1));
        } else {
            fromDate = LocalDateTime.of(YEAR_OF_START, 1, 1, 0, 0, 0);

        }
        LocalDateTime toDate;
        if (toMatcher.find()) {
            toDate = parseISO8601Date(toMatcher.group(1));
        } else {
            toDate = LocalDateTime.now();
        }

        String outputFormat = formatMatcher.find() ? formatMatcher.group(1) : "markdown";

        return new CommandLineArguments(logPath, fromDate, toDate, outputFormat);
    }

    private static LocalDateTime parseISO8601Date(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
    }
}
