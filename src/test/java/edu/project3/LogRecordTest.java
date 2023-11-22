package edu.project3;

import edu.project3.log.LogRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LogRecordTest {
    String sourceLog = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
    String expectedRemoteAddress = "93.180.71.3";
    String expectedRemoteUser = "-";
    LocalDateTime expectedLocalDateTime = LocalDateTime.parse("2015-05-17T08:05:32");
    String expectedRequest = "GET /downloads/product_1 HTTP/1.1";
    int expectedStatus = 304;
    int expectedBodyBytesSend = 0;
    String expectedHttpReferer = "-";
    String expectedHttpUserAgent = "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)";


    String expectedResources = "/downloads/product_1";
    LogRecord expected = new LogRecord(
            expectedRemoteAddress,
            expectedRemoteUser,
            expectedLocalDateTime,
            expectedRequest,
            expectedStatus,
            expectedBodyBytesSend,
            expectedHttpReferer,
            expectedHttpUserAgent
    );

    @Test
    public void parseLogTestSuccess() {
        var result = LogRecord.parse(sourceLog);

        assertEquals(expected, result);

        assertEquals(expectedResources, result.getResource());

    }

    @Test
    public void parseLogException() {
        assertThrows(NullPointerException.class, () -> {
            LogRecord.parse(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LogRecord.parse("invalid");
        });
    }

}
