package server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class HttpHandler {
    private final OutputStream out;
    private final InputStream in;
    private final HTMLparser HTMLparser;

    public HttpHandler(Socket accepted, HTMLparser HTMLparser) throws IOException {
        out=accepted.getOutputStream();
        in=accepted.getInputStream();
        this.HTMLparser=HTMLparser;

    }

    public void startServing() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        List<String> request = br.readAllLines();
        br.close();
        ParsedRequest parsedRequest=new HttpRequestParser().parse(request);
        switch (parsedRequest.method){
            case HTTPMETHOD.GET ->{serveGetReq(parsedRequest);}
            case HTTPMETHOD.POST ->{servePostReq(parsedRequest);}
            default -> {throw new IllegalArgumentException();}
        }

    }

    private void servePostReq(ParsedRequest parsedRequest) {
    }

    private void serveGetReq(ParsedRequest parsedRequest) {
    }
}
