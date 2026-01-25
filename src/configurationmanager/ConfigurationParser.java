package configurationmanager;

import java.io.CharConversionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationParser {
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^\\s*([A-Z0-9_]+)\\s*=\\s*(\"([^\"]*)\"|(\\d+))\\s*$"); //add machers for Dobule and Bool
    private final Path path;
    private final Map<String, Object> parsed;

    public ConfigurationParser(Path path) {
        if(!Objects.nonNull(path)) {
            throw new NullPointerException("Path Vuoto");
        }
        this.path = path;
        try {
            parsed=parse(path);
        } catch (IOException e) {
            throw new RuntimeException("Parsing Failed");
        }
    }

    public String getValue(String key){
        if(parsed.containsKey(key))
            return (String)parsed.get(key);
        throw new NoSuchElementException("No element correspond to the provided key");
    }

    public int getInt(String key){
        if(parsed.containsKey(key)) {
            Object obj = parsed.get(key);
            if(obj instanceof Integer)
                return (int) obj;

            throw new ClassCastException("Integer Conversion Failed");
        }
        throw new NoSuchElementException("No element correspond to the provided key");
    }


    //this is the real parser
    private static Map<String, Object> parse(Path file) throws IOException {
        Map<String, Object> config = new HashMap<>();

        for (String line : Files.readAllLines(file)) {
            line = line.trim();

            // ignora righe vuote e commenti
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            Matcher matcher = LINE_PATTERN.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Row not valid: " + line);
            }

            String key = matcher.group(1);
            String stringValue = matcher.group(3);
            String numberValue = matcher.group(4);

            Object value;
            if (stringValue != null) {
                value = stringValue;
            } else if (numberValue != null) {
                value = Integer.parseInt(numberValue);
            }
            else {
                throw new CharConversionException("Something went wrong");
            }


            config.put(key, value);
        }

        return config;
    }

}
