package configurationmanager;


import configurationmanager.types.IP;
import configurationmanager.types.Name;
import configurationmanager.types.Port;

public interface Configuration {
    String defaultConfigName="server.conf";
    String defaultFolderName="conf";

    IP getHost();
    Port getPort();
    Name getName();
}
