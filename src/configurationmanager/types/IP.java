package configurationmanager.types;

import java.util.regex.Pattern;

public record IP(String value) {
    private static final Pattern IPV4_PATTERN =
            Pattern.compile("^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.)){3}" +
                    "(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$");

    public IP{
        if(value == null)
            throw new NullPointerException("Null string");
        if( ! IPV4_PATTERN.matcher(value).matches())
            throw new IllegalArgumentException("Ip not valid");

    }
}
