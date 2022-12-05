package Templates.RMI.Squares;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        try {
            RemoteCalculator stub = new RemoteCalculator();
            // create the RMI registry on port 5000
            Registry rmiRegistry = LocateRegistry.createRegistry(Constants.PORT_NUMBER);
            // path to access is rmi://localhost:5000/calculator
            rmiRegistry.rebind("calculator", stub);
            System.out.println("Server start");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
