package Templates.RMI.Squares;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCalculator extends UnicastRemoteObject implements CalculatorInt {
    public RemoteCalculator() throws RemoteException {
        super();
    }

    public int areaAddition(Rectangle a, Rectangle b) throws RemoteException {
        int squareAreaA = a.length * a.height;
        int squareAreaB = b.length * b.height;
        return squareAreaA + squareAreaB;
    }

    public int areaSubtraction(Rectangle a, Rectangle b) throws RemoteException {
        int squareAreaA = a.length * a.height;
        int squareAreaB = b.length * b.height;
        return squareAreaA - squareAreaB;
    }

    public int areaMultiplication(Rectangle a, Rectangle b) throws RemoteException {
        int squareAreaA = a.length * a.height;
        int squareAreaB = b.length * b.height;
        return squareAreaA * squareAreaB;
    }

    public int areaDivision(Rectangle a, Rectangle b) throws RemoteException {
        int squareAreaA = a.length * a.height;
        int squareAreaB = b.length * b.height;
        return squareAreaA / squareAreaB;
    }
}
