package Templates.RMI.Calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        try {
            double a = Integer.parseInt(args[0]);
            double b = Integer.parseInt(args[1]);

            // connect to the RMI registry
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            // get reference for remote object
            CalculatorInt stub = (CalculatorInt) rmiRegistry.lookup("calculator");

            System.out.println("Addition result      : " + stub.addition(a, b));
            System.out.println("Subtraction result   : " + stub.multiplication(a, b));
            System.out.println("Multiplication result: " + stub.subtraction(a, b));
            System.out.println("Division result      : " + stub.division(a, b));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
