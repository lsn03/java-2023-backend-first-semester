package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogRecord;
import edu.project3.log.LogReport;
import edu.project3.parser.LogFileParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class LogAnalyzerTest {
    String logPath = "src/test/resources/nginx_demo.txt";
    List<LogRecord> list = LogFileParser.parseLogs(logPath);
    LocalDateTime fromDate = null;
    LocalDateTime toDate = null;

    long expectedRequests = 11l;
    Map<String, Long> expectedResources = Map.of(
            "/downloads/product_1", 8l,
            "/downloads/product_2", 3l
    );
    Map<Integer, Long> expectedResponseCodeCount = Map.of(
            304, 6l,
            404, 2l,
            200, 3l
    );
    Map<String, Long> expectedRemoteAddress = Map.of(
            "93.180.71.3", 4l,
            "80.91.33.133", 2l,
            "217.168.17.5", 5l
    );
    Map<String, Long> expectedHttpUserAgent = Map.of(
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", 4l,
            "Debian APT-HTTP/1.3 (0.8.10.3)", 4l,
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)", 2l,
            "-", 1l
    );

    @Test
    public void logAnalyzerTest() {

        LogReport logReport = LogAnalyzer.analyzeLogs(list, fromDate, toDate, logPath);

        assertEquals(expectedRequests, logReport.getTotalRequests());
        assertEquals(expectedResources, logReport.getResourceCounts());
        assertEquals(expectedResponseCodeCount, logReport.getResponseCodeCounts());
        assertEquals(expectedRemoteAddress, logReport.getRemoteAddresCounts());
        assertEquals(expectedHttpUserAgent, logReport.getHttpUserAgentCount());


    }
}
