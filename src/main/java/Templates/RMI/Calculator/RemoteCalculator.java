package Templates.RMI.Calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCalculator extends UnicastRemoteObject implements CalculatorInt {
    public RemoteCalculator() throws RemoteException {
        super();
    }

    public double addition(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtraction(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiplication(double a, double b) throws RemoteException {
        return a * b;
    }

    public double division(double a, double b) throws RemoteException {
        return a / b;
    }
}
