package configurationmanager;


import configurationmanager.types.Name;
import configurationmanager.types.Port;
import server.HTMLparser;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface Configuration {
    String defaultConfigName="server.conf";
    String defaultFolderName="conf";

    InetAddress getHost() throws UnknownHostException;
    Port getPort();
    Name getName();

    String getLocation();
}
