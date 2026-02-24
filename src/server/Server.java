package server;

import java.io.IOException;

public class Server {

    static void main() {
        try {
            System.out.println("START");
            new ConnectionAccepter().accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
