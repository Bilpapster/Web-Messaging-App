package com;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public Server() throws RemoteException {
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No port provided through command line arguments.");
            System.out.println("Server terminated.");
            return;
        }

        try {
            int port = Integer.parseInt(args[0]);
            RequestProcessor requestProcessor = new RequestProcessor(new MessagingServer());
            Registry rmiRegistry = LocateRegistry.createRegistry(port);
            rmiRegistry.rebind("processor", requestProcessor);
            System.out.println("Server ready");
        } catch (NumberFormatException e) {
            System.out.println("\"" + args[0] + "\"" + " is not an integer! I expected an integer number as a port.");
            System.out.println("Server terminated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
