package RequestProcessorTests;

import com.MessagingServer;
import com.RequestProcessor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public abstract class RequestProcessorTest {
    protected RequestProcessor requestProcessor;
    protected final List<String[]> argsArray = new ArrayList<>();
    protected final Map<Integer, String> activeUsers = new HashMap<>();
    protected final String ip = "localhost";
    protected final String portAsString = "5000";
    protected final Set<String> testUsernames = new HashSet<>();

    public RequestProcessorTest() {
        try {
            requestProcessor = new RequestProcessor(new MessagingServer());
            Registry rmiRegistry = LocateRegistry.createRegistry(5000);
            rmiRegistry.rebind("processor", requestProcessor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
