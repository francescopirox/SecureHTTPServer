package server;

import configurationmanager.Configuration;
import configurationmanager.ConfigurationManager;

import java.io.IOException;
import java.net.ServerSocket;

import java.net.UnknownHostException;

public class ConnectionAccepter {
    private Configuration configuration= new ConfigurationManager();
    private ServerSocket socket;

    public ConnectionAccepter(){
        try {
            socket= new ServerSocket(configuration.getPort().value());
        } catch (UnknownHostException e) {

        }
        catch (IOException ex){

        }

    }

    public void accept() throws IOException {
        while(true) {
            System.out.println("START TO ACCEPT");
            new HttpHandler(socket.accept()).startServing();
        }
    }


    public void close() throws IOException{
        socket.close();
    }
}
