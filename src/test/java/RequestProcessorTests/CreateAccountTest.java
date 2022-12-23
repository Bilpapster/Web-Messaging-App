package RequestProcessorTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CreateAccountTest extends RequestProcessorTest {
    @Test
    void validUsernamesTest() throws RemoteException {
        testUsernames.add("papster");
        testUsernames.add("USERNAME");
        testUsernames.add("____papastva");
        testUsernames.add("m_ar____ia");
        testUsernames.add("nIKos______0");
        testUsernames.add("mike_123___456");
        testUsernames.add("123123824093");

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
    void accountCreationTest() throws RemoteException{
        for (String[] args : argsArray) {
            String serverResponse = requestProcessor.processRequest(args);
            assertEquals("Sorry, the user already exists", serverResponse);
        }
    }

    @Test
    void invalidUsernamesTest() throws RemoteException {
        List<String[]> invalidUsernameArgsArray = new ArrayList<>();
        invalidUsernameArgsArray.add(new String[]{"localhost", "5000", "1", ""});
        invalidUsernameArgsArray.add(new String[]{"localhost", "5000", "1", "άκυρο"});
        invalidUsernameArgsArray.add(new String[]{"localhost", "5000", "1", "inv@-lid"});
        invalidUsernameArgsArray.add(new String[]{"localhost", "5000", "1", "username withSpace"});
        invalidUsernameArgsArray.add(new String[]{"localhost", "5000", "1", "special;Character"});

        for (String[] args : invalidUsernameArgsArray) {
            assertEquals("Invalid Username", requestProcessor.processRequest(args));
        }
    }
}
