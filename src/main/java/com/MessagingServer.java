package com;

import java.util.*;

public class MessagingServer {

    //    private List<Account> storedAccounts = new ArrayList<>();
    private Map<Integer, Account> activeAuthenticationTokens = new HashMap<>();
    private Set<String> activeUsernames = new HashSet<>();

    public static void main(String[] args) {
        while (true) {

            break;
        }
    }

//    public void addAccount() {}

    public void createAccount(String username, int authenticationToken) {
        activeUsernames.add(username);
        activeAuthenticationTokens.put(authenticationToken, new Account(username, authenticationToken));
    }

    public boolean isTokenAvailable(int tokenToCheck) {
        return !activeAuthenticationTokens.containsKey(tokenToCheck);
    }

    public boolean isUsernameAvailable(String usernameToCheck) {
        return !activeUsernames.contains(usernameToCheck);
    }
}
