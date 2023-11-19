package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogRecord;
import edu.project3.log.LogReport;
import edu.project3.parser.LogFileParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

public class LogReportTest {
    String logPath = "src/test/resources/nginx_test.txt";
    List<LogRecord> list = LogFileParser.parseLogs(logPath);

    String expectedAdoc = "== Общая информация\n" +
            "[cols=\"2,1\", options=\"header\"]\n" +
            "|===\n" +
            "|Метрика|Значение\n" +
            "|Файл(-ы)|src/test/resources/nginx_test.txt\n" +
            "|Начальная дата|1970-01-01T00:00\n" +
            "|Конечная дата|2023-01-01T00:00\n" +
            "|Количество запросов|4\n" +
            "|Средний размер ответа|289.75b\n" +
            "|===\n" +
            "\n" +
            "== Запрашиваемые ресурсы\n" +
            "[cols=\"2,1\", options=\"header\"]\n" +
            "|===\n" +
            "|Ресурс|Количество\n" +
            "|`/downloads/product_1`|2\n" +
            "|`/downloads/product_2`|2\n" +
            "|===\n" +
            "\n" +
            "== Коды ответа\n" +
            "[cols=\"3,2,1\", options=\"header\"]\n" +
            "|===\n" +
            "|Код|Имя|Количество\n" +
            "|304|Not Modified|1\n" +
            "|404|Not Found|2\n" +
            "|200|OK|1\n" +
            "|===\n" +
            "\n" +
            "== Используемые удаленные адреса\n" +
            "[cols=\"2,1\", options=\"header\"]\n" +
            "|===\n" +
            "|Адрес|Количество\n" +
            "|`93.180.71.3`|1\n" +
            "|`217.168.17.5`|3\n" +
            "|===\n" +
            "\n" +
            "== Используемые  http_user_agent\n" +
            "[cols=\"2,1\", options=\"header\"]\n" +
            "|===\n" +
            "|Http_user_agent|Количество\n" +
            "|`Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)`|1\n" +
            "|`Debian APT-HTTP/1.3 (0.8.10.3)`|3\n" +
            "|===\n\n";
    String expectedMD = "#### Общая информация\n" +
            "\n" +
            "|Метрика|Значение|\n" +
            "|:----:|----:|\n" +
            "|Файл(-ы)|`src/test/resources/nginx_test.txt`|\n" +
            "|Начальная дата|1970-01-01T00:00|\n" +
            "|Конечная дата|2023-01-01T00:00|\n" +
            "|Количество запросов|4|\n" +
            "|Средний размер ответа|289.75b|\n" +
            "\n" +
            "#### Запрашиваемые ресурсы\n" +
            "\n" +
            "|Ресурс|Количество|\n" +
            "|:----:|----:|\n" +
            "|`/downloads/product_1`|2|\n" +
            "|`/downloads/product_2`|2|\n" +
            "\n" +
            "#### Коды ответа\n" +
            "\n" +
            "|Код|Имя|Количество|\n" +
            "|:----:|:----:|----:|\n" +
            "|304|Not Modified|1|\n" +
            "|404|Not Found|2|\n" +
            "|200|OK|1|\n" +
            "\n" +
            "#### Используемые удаленные адреса\n" +
            "\n" +
            "|Адрес|Количество|\n" +
            "|:----:|----:|\n" +
            "|`93.180.71.3`|1|\n" +
            "|`217.168.17.5`|3|\n" +
            "\n" +
            "#### Используемые  http_user_agent\n" +
            "\n" +
            "|Http_user_agent|Количество|\n" +
            "|:----:|----:|\n" +
            "|`Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)`|1|\n" +
            "|`Debian APT-HTTP/1.3 (0.8.10.3)`|3|\n\n";
    LocalDateTime toDate = LocalDateTime.of(2023, 1, 1, 0, 0);

    @Test
    public void testToDoc() {
        LogReport report = LogAnalyzer.analyzeLogs(list, null, toDate, logPath);
        assertEquals(expectedAdoc, report.toAsciiDoc());
        assertEquals(expectedMD, report.toMarkdown());
    }
}
