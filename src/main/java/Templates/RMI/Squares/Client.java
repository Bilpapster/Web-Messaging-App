package Templates.RMI.Squares;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        try {
            // get reference for remote object
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            CalculatorInt stub = (CalculatorInt) rmiRegistry.lookup("calculator");

            Rectangle r1 = new Rectangle(10, 20);
            Rectangle r2 = new Rectangle(2, 4);

            System.out.println("r1 add r2 = " + stub.add(r1, r2));
            System.out.println("r1 mul r2 = " + stub.mul(r1, r2));
            System.out.println("r1 sub r2 = " + stub.sub(r1, r2));
            System.out.println("r1 div r2 = " + stub.div(r1, r2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
