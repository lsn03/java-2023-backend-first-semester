package edu.project3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LogLauncherTest {
    @Test
    public void giveACmdArgs() {
        String expectedPath = "src/test/resources/nginx*";
        LocalDateTime expectedFrom = LocalDateTime.of(2023, 8, 31, 0, 0);
        String expectedFormat = "markdown";

        String[] args = {"-jar", "nginx-log-stats.jar", "--path", "src/test/resources/nginx*", "--from", "2023-08-31", "--format", "markdown"};
        Application.main(args);
        CommandLineArguments arg = Application.getCommandLineArguments();
        assertEquals(expectedPath, arg.getLogPath());
        assertEquals(expectedFormat, arg.getOutputFormat());
        assertEquals(expectedFrom, arg.getFromDate());

    }

}
