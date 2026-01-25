package configurationmanager;

import configurationmanager.types.IP;
import configurationmanager.types.Name;
import configurationmanager.types.Port;

import java.nio.file.FileSystem;
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
    public IP getHost() {
        return null;
    }

    @Override
    public Port getPort() {
        return null;
    }

    @Override
    public Name getName() {
        return null;
    }
}
