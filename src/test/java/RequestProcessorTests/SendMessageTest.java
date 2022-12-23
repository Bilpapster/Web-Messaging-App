package RequestProcessorTests;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest extends RequestProcessorTest {
    public SendMessageTest() throws RemoteException {
        super();

        testUsernames.add("Nikolakis");
        testUsernames.add("t_a_n_i_a____124");
        testUsernames.add("BillByTaxi");
        testUsernames.add("Anastasiapaokgate4_");

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
    void messageSendingTest() throws RemoteException {
        this.validMessageSending();
        this.invalidMessageSending();
    }

    private void validMessageSending() throws RemoteException {
        // every existent user sends a valid message to everybody else (including self-messaging)
        for (int sender : activeUsers.keySet()) {
            for (String receiver : testUsernames) {
                String senderAsString = Integer.toString(sender);
                String messageBody = "from " + senderAsString + " to " + receiver + " with love.";
                String[] args = new String[]{ip, portAsString, "3", senderAsString, receiver, messageBody};
                String serverResponse = requestProcessor.processRequest(args);
                assertEquals("OK", serverResponse);
            }
        }
    }

    private void invalidMessageSending() throws RemoteException {
        // every existent user sends (invalid) messages to non-existent users
        for (int sender : activeUsers.keySet()) {
            for (String receiver : testUsernames) {
                String senderAsString = Integer.toString(sender);
                receiver = receiver + "___NON_EXISTENT";
                String messageBody = "from " + senderAsString + " to " + receiver + " with love.";
                String[] args = new String[]{ip, portAsString, "3", senderAsString, receiver, messageBody};
                String serverResponse = requestProcessor.processRequest(args);
                assertEquals("User does not exist", serverResponse);
            }
        }
    }
}
