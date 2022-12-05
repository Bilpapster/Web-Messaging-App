package Templates.RMI.Squares;

import java.rmi.*;

public interface CalculatorInt extends Remote {

    int areaAddition(Rectangle a, Rectangle b) throws RemoteException;

    int areaSubtraction(Rectangle a, Rectangle b) throws RemoteException;

    int areaMultiplication(Rectangle a, Rectangle b) throws RemoteException;

    int areaDivision(Rectangle a, Rectangle b) throws RemoteException;
}
