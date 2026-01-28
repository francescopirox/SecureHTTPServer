package configurationmanager.types;

public record Name(String value) {
    public Name{
        if(!value.matches("^\\w{1,30}$"))
            throw new IllegalArgumentException("Value doesent match");
    }
}
