package Templates.SimpleThreadedCS;

/**
 * This is a demo client-side socket connection program for a multi-threaded client-server * architecture based on Runnable interface. The Client sends some messages to the Server * and the Server instantly sends them back to the Client (a.k.a. Echo-Server).
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        // establish a connection
        try (Socket socket = new Socket("localhost", 2021)) {
            // writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            while (!"exit".equalsIgnoreCase(line)) {

                // reading from user
                line = sc.nextLine();

                // sending the user input to server
                out.println(line);
                out.flush();

                // displaying server reply
                System.out.println("Server replied " + in.readLine());
            }
            // closing the scanner object
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
