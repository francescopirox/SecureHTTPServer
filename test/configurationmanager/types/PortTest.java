package configurationmanager.types;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PortTest {
    @Test
    void shouldAcceptMin() {
        assertDoesNotThrow(() -> new Port(DEFAULTS.MinPort));
    }

    @Test
    void shouldAcceptMax() {
        assertDoesNotThrow(() -> new Port(DEFAULTS.MaxPort));
    }

    @Test
    void shouldRejectBelowMin() {
        assertThrows(IllegalArgumentException.class,
                () -> new Port(DEFAULTS.MinPort - 1));
    }

    @Test
    void shouldRejectAboveMax() {
        assertThrows(IllegalArgumentException.class,
                () -> new Port(DEFAULTS.MaxPort + 1));
    }
}