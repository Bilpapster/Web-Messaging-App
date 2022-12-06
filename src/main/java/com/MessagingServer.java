package com;

import java.util.*;

public class MessagingServer {
    private Map<Integer, Account> activeAuthenticationTokens = new HashMap<>();
    private Set<String> activeUsernames = new HashSet<>();
    private int messageCount = 0;

    public static void main(String[] args) {
        while (true) {

            break;
        }
    }

    public void createAccount(String username, int authenticationToken) {
        activeUsernames.add(username);
        activeAuthenticationTokens.put(authenticationToken, new Account(username, authenticationToken));
    }

    public boolean isTokenValid(int tokenToCheck) {
        return activeAuthenticationTokens.containsKey(tokenToCheck);
    }

    public boolean isTokenAvailable(int tokenToCheck) {
        return !activeAuthenticationTokens.containsKey(tokenToCheck);
    }

    public boolean tokenExists(int tokenToCheck) {
        return activeAuthenticationTokens.containsKey(tokenToCheck);
    }

    public boolean isUsernameAvailable(String usernameToCheck) {
        return !activeUsernames.contains(usernameToCheck);
    }

    public boolean usernameExists(String usernameToCheck) {
        return activeUsernames.contains(usernameToCheck);
    }

    public void addMessage(int senderAuthenticationToken, String receiver, String messageBody) {
        String sender = activeAuthenticationTokens.get(senderAuthenticationToken).getUsername();
        Message message = new Message(sender, receiver, messageBody);
        this.activeAuthenticationTokens.get(senderAuthenticationToken).addMessage(message, ++messageCount);
    }

    public String printActiveUsernames() {
        String[] usernames = (String []) activeUsernames.stream().sorted().toArray();
        int userID = 1;
        StringBuilder output = new StringBuilder();

        for (String username : usernames) {
            output.append(userID++).append(". ").append(username);
        }
        return output.toString();
    }

    public String printInboxMessages(int userAuthenticationToken) {
        return activeAuthenticationTokens.get(userAuthenticationToken).inboxToString();
    }
}
