package edu.hw6;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class PortScannerTest {
    @Test
    public void portScannerTest() {
        PortScanner portScanner = new PortScanner();
        assertDoesNotThrow(
                () -> portScanner.getPortsInfo()
        );
    }
}
