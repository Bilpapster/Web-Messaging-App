package Templates.RMI.Calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
// To change the 2 input parameters (double numbers) in Intellij go to: Edit Configurations -> Client -> Built and Run

public class Client {
    public static void main(String args[]) {
        try {
            double a = Integer.valueOf(args[0]);
            double b = Integer.valueOf(args[1]);

            // connect to the RMI registry
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            // get reference for remote object
            CalculatorInt stub = (CalculatorInt) rmiRegistry.lookup("calculator");

            System.out.println("Addition result      : " + stub.add(a, b));
            System.out.println("Subtraction result   : " + stub.mul(a, b));
            System.out.println("Multiplication result: " + stub.sub(a, b));
            System.out.println("Division result      : " + stub.div(a, b));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
