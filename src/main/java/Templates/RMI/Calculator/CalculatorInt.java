package Templates.RMI.Calculator;

import java.rmi.*;

public interface CalculatorInt extends Remote {

    public double addition(double a, double b) throws RemoteException;

    public double subtraction(double a, double b) throws RemoteException;

    public double multiplication(double a, double b) throws RemoteException;

    public double division(double a, double b) throws RemoteException;
}
