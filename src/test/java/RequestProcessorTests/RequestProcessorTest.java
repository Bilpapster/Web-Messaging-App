package RequestProcessorTests;

import com.MessagingServer;
import com.RequestProcessor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RequestProcessorTest {
    protected RequestProcessor requestProcessor;
    protected List<String[]> argsArray = new ArrayList<>();
    protected Map<Integer, String> activeUsers = new HashMap<>();

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
