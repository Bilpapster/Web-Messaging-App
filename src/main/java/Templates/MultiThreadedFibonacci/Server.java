package Templates.MultiThreadedFibonacci;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = null;
        // the first argument is the port to listen on
        String port = args[0];
        try {
            socket = new ServerSocket(Integer.parseInt(port));
            System.out.println("Server is now running @ " + args[0]);
            while (true) {
                Socket clientConnection = socket.accept();
                ServerThread st = new ServerThread(clientConnection);
                st.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert socket != null;
            if (!socket.isClosed()) socket.close();
        }
    }
}
