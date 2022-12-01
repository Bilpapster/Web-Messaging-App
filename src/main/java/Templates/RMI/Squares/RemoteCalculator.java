package Templates.RMI.Squares;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCalculator extends UnicastRemoteObject implements CalculatorInt {
    public RemoteCalculator() throws RemoteException {
        super();
    }

    public int add(Rectangle a, Rectangle b) throws RemoteException {
        int square_1_area = a.x * a.y;
        int square_2_area = b.x * b.y;
        return square_1_area + square_2_area;
    }

    public int sub(Rectangle a, Rectangle b) throws RemoteException {
        int square_1_area = a.x * a.y;
        int square_2_area = b.x * b.y;
        return square_1_area - square_2_area;
    }

    public int mul(Rectangle a, Rectangle b) throws RemoteException {
        int square_1_area = a.x * a.y;
        int square_2_area = b.x * b.y;
        return square_1_area * square_2_area;
    }

    public int div(Rectangle a, Rectangle b) throws RemoteException {
        int square_1_area = a.x * a.y;
        int square_2_area = b.x * b.y;
        return square_1_area / square_2_area;
    }
}
