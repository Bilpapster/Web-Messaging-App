package RequestProcessorTests;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowAccountsTest extends RequestProcessorTest {
    public ShowAccountsTest() throws RemoteException {
        super();

        testUsernames.add("Vasilis");
        testUsernames.add("Maria");
        testUsernames.add("Kostas123");
        testUsernames.add("Ana_stasios");

        for (String testUsername : testUsernames) {
            argsArray.add(new String[]{ip, portAsString, "1", testUsername});
        }

        for (String[] args : argsArray) {
            String serverResponse = requestProcessor.processRequest(args);
            try {
                int authenticationToken = Integer.parseInt(serverResponse);
                this.activeUsers.put(authenticationToken, args[3]);
            } catch (NumberFormatException e) {
                System.out.println("Server response: " + serverResponse);
                assertEquals("Expected server response to be an integer",
                        "Actual server response was not an integer");
            }
        }
    }

    @Test
    void accountsShowTest() throws RemoteException {
        StringBuilder expectedOutput = new StringBuilder();
        int userCounter = 0;
        for (String testUsername : testUsernames) {
            expectedOutput.append(++userCounter).append(". ").append(testUsername).append('\n');
        }

        for (int authenticationToken : activeUsers.keySet()) {
            String serverResponse = requestProcessor.processRequest(new String[]{ip, portAsString, "2", Integer.toString(authenticationToken)});
            assertEquals(expectedOutput.toString(), serverResponse);
        }
    }
}
