package com;

import java.util.*;

public class MessagingServer {

    private List<Account> storedAccounts = new ArrayList<>();
    private Map<Integer, Account> activeAuthenticationTokens = new HashMap<>();
    private Set<String> activeUsernames = new HashSet<>();

    public static void main(String[] args) {

    }

    public void addAccount(Account account) {
        this.storedAccounts.add(account);
    }
}
