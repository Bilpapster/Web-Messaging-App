package RequestProcessorTests;

import com.RandomEngine;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowInboxTest extends RequestProcessorTest {
    private final Map<String, StringBuilder> sentMessages = new HashMap<>();
    private final int normalizedMessageProbability = 8; //  normalized in [1, 10]

    public ShowInboxTest() throws RemoteException {
        super();
        initializeTestUsernames();

        for (String testUsername : testUsernames) {
            argsArray.add(new String[]{ip, portAsString, "1", testUsername});
            sentMessages.put(testUsername, new StringBuilder());
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
    void inboxShowTest() throws RemoteException {
        int messageCount = 0;

        // every existent user sends a valid message to everybody else (including self-messaging)
        // with a specified message probability p, normalized in [1, 10]
        // that means the sender sends the message to the receiver with probability p/10
        // the sender does NOT send the message to the receiver with probability (1-p/10)
        for (int sender : activeUsers.keySet()) {
            for (String receiver : testUsernames) {
                int randomNumber = RandomEngine.getInstance().nextInt(10) + 1;
                if (randomNumber > normalizedMessageProbability) {
                    continue;
                }

                String senderAsString = Integer.toString(sender);
                String messageBody = "from " + senderAsString + " to " + receiver + " with love.";
                String[] args = new String[]{ip, portAsString, "3", senderAsString, receiver, messageBody};
                String serverResponse = requestProcessor.processRequest(args);
                assertEquals("OK", serverResponse);

                String senderUsername = activeUsers.get(sender);
                sentMessages.get(receiver).append(++messageCount).append(". from: ").append(senderUsername).append("*\n");
            }
        }

        for (int authenticationToken : activeUsers.keySet()) {
            String[] args = new String[]{ip, portAsString, "4", Integer.toString(authenticationToken)};
            String serverResponse = requestProcessor.processRequest(args);
            assertEquals(sentMessages.get(activeUsers.get(authenticationToken)).toString(), serverResponse);
        }
        System.out.println(messageCount);
    }

    private void initializeTestUsernames() {
        testUsernames.add("giraffe");
        testUsernames.add("elephant");
        testUsernames.add("zebra");
        testUsernames.add("lion");
        testUsernames.add("hippo");
        testUsernames.add("rino");
        testUsernames.add("ant");
        testUsernames.add("cat");
        testUsernames.add("dog");
        testUsernames.add("spider");
        testUsernames.add("horse");
        testUsernames.add("dinosaur");
        testUsernames.add("fly");
        testUsernames.add("rabbit");
        testUsernames.add("turtle");
        testUsernames.add("whale");
        testUsernames.add("chicken");
        testUsernames.add("rooster");
        testUsernames.add("squirrel");
        testUsernames.add("sparrow");
        testUsernames.add("crab");
        testUsernames.add("peacock");
        testUsernames.add("panda");
        testUsernames.add("bull");
        testUsernames.add("shark");
        testUsernames.add("owl");
        testUsernames.add("deer");
        testUsernames.add("reindeer");
        testUsernames.add("sheep");
        testUsernames.add("dolphin");
        testUsernames.add("wolf");
        testUsernames.add("fox");
        testUsernames.add("alpaca");
        testUsernames.add("kangaroo");
        testUsernames.add("parrot");
    }
}








