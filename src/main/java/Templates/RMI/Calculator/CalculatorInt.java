package Templates.RMI.Calculator;

import java.rmi.*;

public interface CalculatorInt extends Remote {

    double addition(double a, double b) throws RemoteException;

    double subtraction(double a, double b) throws RemoteException;

    double multiplication(double a, double b) throws RemoteException;

    double division(double a, double b) throws RemoteException;
}
