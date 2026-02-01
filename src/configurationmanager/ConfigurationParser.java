package configurationmanager;

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
            "^\\s*([A-Z0-9_]+)\\s*=\\s*(?:\"([^\"]*)\"|([+-]?\\d+)|([+-]?\\d+\\.\\d+)|(?i)(true|false))\\s*$"
    );
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

    public String castValue(String key){
        if(parsed.containsKey(key))
            return (String)parsed.get(key);
        throw new NoSuchElementException("No element correspond to the provided key");
    }

    public String getValue(String key) {
        if (parsed.containsKey(key)) {
            return parsed.get(key).toString();
        }
        return null;
    }

    public boolean checkValue(String key){
        return parsed.containsKey(key);
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

    public Integer getInteger(String key){
        if(parsed.containsKey(key)) {
            Object obj = parsed.get(key);
            if(obj instanceof Integer)
                return (Integer) obj;

            throw new ClassCastException("Integer Conversion Failed");
        }
        return null;
    }

    public double getDouble(String key){
        if(parsed.containsKey(key)) {
            Object obj = parsed.get(key);
            if(obj instanceof Double)
                return (Double)obj;

            throw new ClassCastException("Double Conversion Failed");
        }
        throw new NoSuchElementException("No element correspond to the provided key");
    }

    public boolean getBoolean(String key){
        if(parsed.containsKey(key)) {
            Object obj = parsed.get(key);
            if(obj instanceof Boolean)
                return (boolean) obj;

            throw new ClassCastException("Boolean Conversion Failed");
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
            //parser
            String stringValue = matcher.group(2);
            String intValue    = matcher.group(3);
            String doubleValue = matcher.group(4);
            String boolValue   = matcher.group(5);

            Object value = castValue(stringValue, intValue,doubleValue,boolValue);
            config.put(key, value);
        }

        return config;
    }

    private static Object castValue(String stringValue, String intValue, String doubleValue, String boolValue) {
        Object value;
        if (stringValue != null) {
            value = stringValue;
        }
        else if (intValue != null) {
            value = Integer.parseInt(intValue);
        }
        else if (doubleValue != null) {
            value = Double.parseDouble(doubleValue);
        }
        else if (boolValue != null) {
            value = Boolean.parseBoolean(boolValue);
        }
        else {
            throw new IllegalArgumentException("Unknown value type");
        }
        return value;
    }

}
