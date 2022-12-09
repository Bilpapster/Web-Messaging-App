package com;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommunicationProtocol extends Remote {
    String processRequest(String[] args) throws RemoteException;
}
