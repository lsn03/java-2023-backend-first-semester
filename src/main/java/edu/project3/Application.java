package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogRecord;
import edu.project3.log.LogReport;
import edu.project3.parser.ArgumentParser;
import edu.project3.parser.LogFileParser;
import java.util.List;

public final class Application {

    private static CommandLineArguments commandLineArguments;
    private static List<LogRecord> logRecords;
    private static LogReport logReport;


    private Application() {

    }

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        commandLineArguments = ArgumentParser.parseDataFromArgs(args);


        logRecords = LogFileParser.parseLogs(commandLineArguments.getLogPath());
        logReport = LogAnalyzer.analyzeLogs(
                logRecords,
                commandLineArguments.getFromDate(),
                commandLineArguments.getToDate(),
                commandLineArguments.getLogPath()
        );

        print(logReport);

    }

    public static CommandLineArguments getCommandLineArguments() {
        return commandLineArguments;
    }

    public static List<LogRecord> getLogRecords() {
        return logRecords;
    }

    public static LogReport getLogReport() {
        return logReport;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void print(LogReport logReport) {

        switch (commandLineArguments.getOutputFormat()) {
            case "markdown" -> System.out.println(logReport.toMarkdown());
            case "adoc" -> System.out.println(logReport.toAsciiDoc());
            default -> throw new IllegalArgumentException();
        }
    }


}
