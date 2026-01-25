package configurationmanager.types;

import configurationmanager.types.IP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPTest {

    // -------- VALID IP --------

    @Test
    void shouldCreateIpForValidAddress() {
        IP ip = new IP("192.168.1.1");
        assertEquals("192.168.1.1", ip.value());
    }

    @Test
    void shouldAcceptLoopback() {
        assertDoesNotThrow(() -> new IP("127.0.0.1"));
    }

    @Test
    void shouldAcceptMinAndMaxValues() {
        assertDoesNotThrow(() -> new IP("0.0.0.0"));
        assertDoesNotThrow(() -> new IP("255.255.255.255"));
    }

    // -------- NULL --------

    @Test
    void shouldThrowNullPointerExceptionWhenValueIsNull() {
        NullPointerException ex =
                assertThrows(NullPointerException.class, () -> new IP(null));

        assertEquals("Null string", ex.getMessage());
    }

    // -------- INVALID IP --------

    @Test
    void shouldRejectIpWithTooHighOctet() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> new IP("256.1.1.1"));

        assertEquals("Ip not valid", ex.getMessage());
    }

    @Test
    void shouldRejectIncompleteIp() {
        assertThrows(IllegalArgumentException.class, () -> new IP("192.168.1"));
    }

    @Test
    void shouldRejectNonNumericIp() {
        assertThrows(IllegalArgumentException.class, () -> new IP("abc.def.ghi.jkl"));
    }

    @Test
    void shouldRejectIpWithExtraDots() {
        assertThrows(IllegalArgumentException.class, () -> new IP("192.168.1.1."));
    }

    @Test
    void shouldRejectIpWithLeadingZeros() {
        assertThrows(IllegalArgumentException.class, () -> new IP("01.02.03.04"));
    }
}