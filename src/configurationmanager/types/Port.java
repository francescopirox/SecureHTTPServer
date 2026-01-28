package configurationmanager.types;

public record Port(int value) {
    public Port{
        if(value<DEFAULTS.MinPort || value>DEFAULTS.MaxPort)
            throw new IllegalArgumentException("Port Number Not valid");
    }
}
