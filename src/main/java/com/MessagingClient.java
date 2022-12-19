package com;

import java.io.IOException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class MessagingClient {
    private Account userAccount;

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("No port provided through command line arguments.");
            System.out.println("Client terminated.");
            return;
        }

        try {
            int port = Integer.parseInt(args[0]);
            Registry rmiRegistry = LocateRegistry.getRegistry(port);
            CommunicationProtocol stub = (CommunicationProtocol) rmiRegistry.lookup("processor");
            System.out.println(stub.processRequest(args));
        } catch (NumberFormatException e) {
            System.out.println("\"" + args[0] + "\"" + " is not an integer! I expected an integer number as a port.");
            System.out.println("Client terminated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}