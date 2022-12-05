package Templates.RMI.Squares;

import java.rmi.*;

public interface CalculatorInt extends Remote {

    public int areaAddition(Rectangle a, Rectangle b) throws RemoteException;

    public int areaSubtraction(Rectangle a, Rectangle b) throws RemoteException;

    public int areaMultiplication(Rectangle a, Rectangle b) throws RemoteException;

    public int areaDivision(Rectangle a, Rectangle b) throws RemoteException;
}
