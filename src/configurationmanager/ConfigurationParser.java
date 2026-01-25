package configurationmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationParser {
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^\\s*([A-Z0-9_]+)\\s*=\\s*(\"([^\"]*)\"|(\\d+))\\s*$");
    private final Path path;
    public ConfigurationParser(Path path) {
        if(Objects.nonNull(path)) {
            path = path;
        }
        throw new NullPointerException("Path Vuoto");
    }
    public static Map<String, Object> parse(Path file) throws IOException {
        Map<String, Object> config = new HashMap<>();

        for (String line : Files.readAllLines(file)) {
            line = line.trim();

            // ignora righe vuote e commenti
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            Matcher matcher = LINE_PATTERN.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Riga non valida: " + line);
            }

            String key = matcher.group(1);
            String stringValue = matcher.group(3);
            String numberValue = matcher.group(4);

            Object value;
            if (stringValue != null) {
                value = stringValue;
            } else {
                value = Integer.parseInt(numberValue);
            }

            config.put(key, value);
        }

        return config;
    }

}
