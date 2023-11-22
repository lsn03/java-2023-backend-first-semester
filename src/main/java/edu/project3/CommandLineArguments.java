package edu.project3;

import java.time.LocalDateTime;

public class CommandLineArguments {
    private final String logPath;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;
    private final String outputFormat;

    public CommandLineArguments(String logPath, LocalDateTime fromDate, LocalDateTime toDate, String outputFormat) {
        this.logPath = logPath;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.outputFormat = outputFormat;
    }

    public String getLogPath() {
        return logPath;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public String getOutputFormat() {
        return outputFormat;
    }
}
