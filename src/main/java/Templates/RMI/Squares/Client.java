package Templates.RMI.Squares;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        try {
            // get reference for remote object
            Registry rmiRegistry = LocateRegistry.getRegistry(Constants.PORT_NUMBER);
            CalculatorInt stub = (CalculatorInt) rmiRegistry.lookup("calculator");

            Rectangle r1 = new Rectangle(10, 20);
            Rectangle r2 = new Rectangle(2, 4);

            System.out.println("r1 add r2 = " + stub.areaAddition(r1, r2));
            System.out.println("r1 mul r2 = " + stub.areaMultiplication(r1, r2));
            System.out.println("r1 sub r2 = " + stub.areaSubtraction(r1, r2));
            System.out.println("r1 div r2 = " + stub.areaDivision(r1, r2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
