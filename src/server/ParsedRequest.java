package server;

import java.util.Objects;

public class ParsedRequest {
    public final HTTPMETHOD method;
    public final String request_target;
    public  final HTTPVERSION protocol_version;
    private final HttpHeaders headers;
    private final byte[] body;


    public ParsedRequest(String rawRequest){
        Objects.requireNonNull(rawRequest, "Raw request cannot be null");

        String[] sections = rawRequest.split("\r\n\r\n", 2);
        if (sections.length == 0) {
            throw new IllegalArgumentException("Malformed HTTP request");
        }

        String headerPart = sections[0];
        String bodyPart = sections.length == 2 ? sections[1] : "";

        String[] lines = headerPart.split("\r\n");
        if (lines.length == 0) {
            throw new IllegalArgumentException("Missing request line");
        }
        body= bodyPart.getBytes();

        String reqLine = lines[0];
        String[] part=reqLine.split("0");
        if(part.length != 3)
            throw new IllegalArgumentException();

        this.method = HTTPMETHOD.valueOf(part[0]);
        this.request_target=part[1];
        this.protocol_version=HTTPVERSION.valueOf(part[2]);
        this.headers=new HttpHeaders(lines);


    }

    public HTTPMETHOD getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return request_target;
    }

    public HTTPVERSION getVersion() {
        return protocol_version;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body.clone();
    }

}
