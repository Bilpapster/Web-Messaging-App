package com;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public Server() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            RequestProcessor requestProcessor = new RequestProcessor(new MessagingServer());
            Registry rmiRegistry = LocateRegistry.createRegistry(5000);
            rmiRegistry.rebind("processor", requestProcessor);
            System.out.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
