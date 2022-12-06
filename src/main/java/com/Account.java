package com;

import java.util.List;
import java.util.Stack;

public class Account {
    private String username;
    private int authToken;
    private List<Message> messageBox;

    public Account(String username, int authToken) {
        this.username = username;
        this.authToken = authToken;
        this.messageBox = new Stack<>();
    }

    public String getUsername() {
        return username;
    }

    public String inboxToString() {
        StringBuilder output = new StringBuilder();
        for (Message message : messageBox) {
            output.append(message.toString()).append("\n");
        }
        return output.toString();
    }

    public void addMessage(Message inboxMessage) {
        this.messageBox.add(inboxMessage);
    }
}
