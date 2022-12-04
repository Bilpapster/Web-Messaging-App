package Templates.MultiThreadedFibonacci;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket socket;
    private Fibonacci fibonacci;
    String clientIP;

    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
        clientIP = socket.getInetAddress().getHostAddress();
        fibonacci = new Fibonacci();
    }

    private String handleInput(String input) {
        if (input.equalsIgnoreCase("y")) {
            String nextNumber = String.valueOf(fibonacci.getNext());
            return nextNumber;
        }
        return null;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            // send the first number
            int nextFibNumber = fibonacci.getNext();
            out.println(nextFibNumber);
            while (true) {
                String input = in.readLine();
                String response = handleInput(input);
                // continue if the user wants to continue
                // or close the connection
                if (response != null) out.println(response);
                else break;
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Terminating connection. Client: " + clientIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
