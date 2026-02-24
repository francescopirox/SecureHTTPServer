package configurationmanager;

import configurationmanager.types.DEFAULTS;
import configurationmanager.types.Name;
import configurationmanager.types.Port;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ConfigurationManager implements Configuration{

    public Path path;
    public ConfigurationParser configurationParser;
    public ConfigurationManager() {
        path= FileSystems.getDefault().getPath(defaultFolderName,defaultConfigName);
        configurationParser=new ConfigurationParser(path);

    }

    @Override
    public InetAddress getHost() throws UnknownHostException {
        try {
            return InetAddress.getByName(configurationParser.castValue("host"));
        }
        catch (UnknownHostException UHE){
            return InetAddress.getLocalHost();
        }
    }

    @Override
    public Port getPort() {
        Integer x=configurationParser.getInteger("port");
        if(x == null){
            return new Port(DEFAULTS.DefaultPort);
        }
        return new Port(x);
    }

    @Override
    public Name getName() {
        String name=configurationParser.castValue("name");
        if(name == null)
            return new Name(DEFAULTS.defaultName);
        return new Name(name);

    }

    @Override
    public String getLocation(){
        String ret=configurationParser.castValue("location");
        if(ret==null)
            return DEFAULTS.defaultLocation;
        return ret;

    }
}
