package server;

import configurationmanager.Configuration;
import configurationmanager.ConfigurationManager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionAccepter {
    private Configuration configuration= new ConfigurationManager();
    private Socket socket;

    public ConnectionAccepter(){
        try {
            socket= new Socket(configuration.getHost(),configuration.getPort().value());
        } catch (UnknownHostException e) {

        }
        catch (IOException ex){

        }

    }
}
