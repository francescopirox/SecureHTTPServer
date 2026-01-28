package configurationmanager.types;

import configurationmanager.ConfigurationParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationParserTest {

    @Test
    void shouldParseAllSupportedTypes(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("app.conf");

        Files.writeString(cfg, """
            HOST = "1.2.3.4"
            PORT = 8080
            PI = 3.14
            ENABLED = true
            """);

        ConfigurationParser parser = new ConfigurationParser(cfg);

        assertEquals("1.2.3.4", parser.getValue("HOST"));
        assertEquals(8080, parser.getInt("PORT"));
        assertEquals(3.14, parser.getDouble("PI"));
        assertTrue(parser.getBoolean("ENABLED"));
    }

    @Test
    void shouldRejectInvalidRow(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("bad.conf");

        Files.writeString(cfg, "BAD = 12.12.12");

        assertThrows(IllegalArgumentException.class,
                () -> new ConfigurationParser(cfg));
    }

    @Test
    void shouldThrowIfKeyNotFound(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("missing.conf");

        Files.writeString(cfg, "PORT = 8080");

        ConfigurationParser parser = new ConfigurationParser(cfg);

        assertThrows(NoSuchElementException.class,
                () -> parser.getInt("HOST"));
    }

    @Test
    void shouldFailOnWrongType(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("wrong.conf");

        Files.writeString(cfg, """
            PORT = 8080
            ENABLED = true
            """);

        ConfigurationParser parser = new ConfigurationParser(cfg);

        assertThrows(ClassCastException.class,
                () -> parser.getBoolean("PORT"));

        assertThrows(ClassCastException.class,
                () -> parser.getDouble("ENABLED"));
    }

    @Test
    void shouldIgnoreCommentsAndEmptyLines(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("comments.conf");

        Files.writeString(cfg, """
            # This is a comment

            HOST = "server"
            
            # Another comment
            PORT = 9000
            """);

        ConfigurationParser parser = new ConfigurationParser(cfg);

        assertEquals("server", parser.getValue("HOST"));
        assertEquals(9000, parser.getInt("PORT"));
    }

    @Test
    void shouldRejectMalformedLine(@TempDir Path dir) throws IOException {
        Path cfg = dir.resolve("malformed.conf");

        Files.writeString(cfg, "HOST: localhost");

        assertThrows(IllegalArgumentException.class,
                () -> new ConfigurationParser(cfg));
    }
}
