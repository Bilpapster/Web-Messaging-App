package Templates.RMI.Squares;

import java.rmi.*;

public interface CalculatorInt extends Remote {

    public int add(Rectangle a, Rectangle b) throws RemoteException;

    public int sub(Rectangle a, Rectangle b) throws RemoteException;

    public int mul(Rectangle a, Rectangle b) throws RemoteException;

    public int div(Rectangle a, Rectangle b) throws RemoteException;
}
