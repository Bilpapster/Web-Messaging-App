package MultiThreadedFibonacci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        ArrayList<Integer> fiboSeries = new ArrayList<Integer>();

        try {
            // the first argument is the ip address of the server
            // while the second one is its port
            socket = new Socket(args[0], Integer.parseInt(args[1]));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.err.println("Could not initiate a connection to server");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            fiboSeries.add(Integer.parseInt(fromServer));
            System.out.println("Server: " + fiboSeries.toString() + ". Continue?[y/n]");
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }
            // if the user does not want to continue exit the main loop
            if (!fromUser.equals("y")) break;
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
