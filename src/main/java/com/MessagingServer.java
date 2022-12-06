package com;

import java.util.*;

public class MessagingServer {
    private Map<Integer, Account> activeAuthenticationTokens = new HashMap<>();
    private Set<String> activeUsernames = new HashSet<>();

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

    public String printActiveUsernames() {
        String[] usernames = (String []) activeUsernames.stream().sorted().toArray();
        int userID = 1;
        StringBuilder output = new StringBuilder();

        for (String username : usernames) {
            output.append(userID++).append(". ").append(username);
        }
        return output.toString();
    }
}
