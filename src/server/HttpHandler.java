package server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class HttpHandler {
    private final OutputStream out;
    private final InputStream in;
    private final HTMLparser HTMLparser= new HTMLparser("location");

    public HttpHandler(Socket accepted) throws IOException {
        out=accepted.getOutputStream();
        in=accepted.getInputStream();

    }

    public void startServing() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        List<String> request = br.readAllLines();
        br.close();
        ParsedRequest parsedRequest=new HttpRequestParser().parse(request);
        switch (parsedRequest.method){
            case HTTPMETHOD.GET ->{};
            case HTTPMETHOD.POST ->{};
            default -> {};
        }

    }
}
