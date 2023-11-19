package edu.project3;

import edu.project3.parser.ArgumentParser;
import edu.project3.parser.LogFileParser;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class ParserTest {
    @Test
    public void parseArgCommandsTest1() {
        String expectedPath = "src/test/resources/nginx*";
        LocalDateTime expectedFrom = LocalDateTime.of(2010, 8, 31, 0, 0);
        String expectedFormat = "markdown";

        String[] args = {"-jar", "nginx-log-stats.jar", "--path", "src/test/resources/nginx*", "--from", "2010-08-31", "--format", "markdown"};

        var commandLineArguments = ArgumentParser.parseDataFromArgs(args);
        assertEquals(expectedPath, commandLineArguments.getLogPath());
        assertEquals(expectedFormat, commandLineArguments.getOutputFormat());
        assertEquals(expectedFrom, commandLineArguments.getFromDate());

    }

    @Test
    public void parseArgCommandsTest2() {
        String expectedPath = "src/test/resources/nginx*";
        LocalDateTime expectedFrom = LocalDateTime.of(2010, 8, 31, 0, 0);
        LocalDateTime expectedTo = LocalDateTime.of(2011, 8, 31, 0, 0);
        String expectedFormat = "adoc";

        String[] args = {"-jar", "nginx-log-stats.jar", "--path", "src/test/resources/nginx*", "--from", "2010-08-31", "--to", "2011-08-31", "--format", "adoc"};

        var commandLineArguments = ArgumentParser.parseDataFromArgs(args);
        assertEquals(expectedPath, commandLineArguments.getLogPath());
        assertEquals(expectedFormat, commandLineArguments.getOutputFormat());
        assertEquals(expectedFrom, commandLineArguments.getFromDate());
        assertEquals(expectedTo, commandLineArguments.getToDate());

    }

    @Test
    public void parseArgCommandsTest3() {
        String expectedPath = "src/test/resources/nginx*";

        String[] args = {"-jar", "nginx-log-stats.jar", "--path", "src/test/resources/nginx*"};

        var commandLineArguments = ArgumentParser.parseDataFromArgs(args);
        assertEquals(expectedPath, commandLineArguments.getLogPath());
        assertEquals("markdown", commandLineArguments.getOutputFormat());

    }

    @Test
    public void parseArgCommandsTest4() {


        String[] args = {"-jar", "nginx-log-stats.jar",};

        var commandLineArguments = ArgumentParser.parseDataFromArgs(args);
        assertNull(commandLineArguments.getLogPath());


    }

    @Test
    public void parseArgCommandsTest5() {
        String expectedPath = "src/test/resources/nginx_demo.txt";

        assertDoesNotThrow(
                () -> LogFileParser.parseLogs(expectedPath)
        );

    }

    @Test
    @DisplayName("wrong format")
    public void parseArgCommandsTest6() {
        String expectedPath = "src/test/resources/ndddsdsdsademo.txt";

        assertThrows(RuntimeException.class,
                () -> LogFileParser.parseLogs(expectedPath)
        );

    }

    @Test
    public void parseFileTest1() {
        String expectedPath = "src/test/resources/nginx_demo.txt";
        var res = LogFileParser.parseLogs(expectedPath);
        assertEquals(11, res.size());
    }

    @Test
    public void parseFileTest2() {
        String expectedPath = "src/test/resources/nginx_d*";
        var res = LogFileParser.parseLogs(expectedPath);
        assertEquals(11, res.size());
    }

    @Test
    public void parseFileTest3() {
        String expectedPath = "src/test/resources/nginx_d";
        assertThrows(RuntimeException.class, () -> LogFileParser.parseLogs(expectedPath));
    }

    @Test
    public void parseHttpSucces() {
        String expectedPath = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        assertDoesNotThrow(() -> LogFileParser.parseLogs(expectedPath));
    }

    @Test
    public void parseHttpUnSuccess() {
        String expectedPath = "https://123dsaghajdaJK";
        assertThrows(RuntimeException.class, () -> LogFileParser.parseLogs(expectedPath));
    }

    @Test
    public void nullPath() {
        String expectedPath = null;
        assertThrows(NullPointerException.class, () -> LogFileParser.parseLogs(expectedPath));
    }
}
