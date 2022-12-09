package com;

import java.io.IOException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class MessagingClient {
    private Account userAccount;

    public static void main(String[] args) throws IOException {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            CommunicationProtocol stub = (CommunicationProtocol) rmiRegistry.lookup("processor");
            System.out.println(stub.processRequest(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// papster(126), papastva(252), maria(136), nikos(271)

}