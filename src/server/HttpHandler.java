package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpHandler {
    private OutputStream out;
    public HttpHandler(Socket accepted) throws IOException {
        out=accepted.getOutputStream();
        new PrintWriter(out).println("AAAAAA");
    }
}
