package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class LogReport {

    private String logPath;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private long totalRequests;
    private double avgResponseSize;
    private Map<String, Long> resourceCounts;
    private Map<Integer, Long> responseCodeCounts;

    public LogReport(String logPath, LocalDateTime fromDate, LocalDateTime toDate, long totalRequests, double avgResponseSize, Map<String, Long> resourceCounts, Map<Integer, Long> responseCodeCounts) {
        this.logPath = logPath;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalRequests = totalRequests;
        this.avgResponseSize = avgResponseSize;
        this.resourceCounts = resourceCounts;
        this.responseCodeCounts = responseCodeCounts;
    }

    public String toMarkdown() {

        StringBuilder markdownReport = new StringBuilder();
        markdownReport.append("#### Общая информация\n\n");
        markdownReport.append("|        Метрика        |     Значение |\n");
        markdownReport.append("|:---------------------:|-------------:|\n");
        markdownReport.append("|       Файл(-ы)        | `").append(logPath).append("` |\n");
        markdownReport.append("|    Начальная дата     |   ").append(fromDate).append(" |\n");
        markdownReport.append("|     Конечная дата     |   ").append(toDate).append(" |\n");
        markdownReport.append("|  Количество запросов  |       ").append(totalRequests).append(" |\n");
        markdownReport.append("| Средний размер ответа |         ").append(avgResponseSize).append("b |\n\n");

        markdownReport.append("#### Запрашиваемые ресурсы\n\n");
        markdownReport.append("|     Ресурс      | Количество |\n");
        for (Map.Entry<String, Long> entry : resourceCounts.entrySet()) {
            markdownReport.append("|  `").append(entry.getKey()).append("`  |      ").append(entry.getValue()).append(" |\n");
        }
        markdownReport.append("\n");

        markdownReport.append("#### Коды ответа\n\n");
        markdownReport.append("| Код |          Имя          | Количество |\n");
        for (Map.Entry<Integer, Long> entry : responseCodeCounts.entrySet()) {
            markdownReport.append("| ").append(entry.getKey()).append(" |          -           |       ").append(entry.getValue()).append(" |\n");
        }

        return markdownReport.toString();
    }

    public String toAsciiDoc() {

        StringBuilder asciiDocReport = new StringBuilder();


        return asciiDocReport.toString();
    }
}
