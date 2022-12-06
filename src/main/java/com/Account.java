package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Account {
    private String username;
    private int authToken;
    private List<Message> messageBox;
    private List<Integer> messageIDs;

    public Account(String username, int authToken) {
        this.username = username;
        this.authToken = authToken;
        this.messageBox = new ArrayList<>();
        this.messageIDs = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String inboxToString() {
        StringBuilder output = new StringBuilder();
        int index = 0;
        for (Message message : messageBox) {
            output.append(messageIDs.get(index++)).append(". ").append(message.getMessageSignature()).append("\n");
        }
        return output.toString();
    }

    public void addMessage(Message inboxMessage, int messageID) {
        this.messageBox.add(inboxMessage);
        this.messageIDs.add(messageID);
    }

    public boolean existsMessageID(int messageIDToCheck) {
        return messageIDs.contains(messageIDToCheck);
    }

    public String readMessage(int messageID) {
        int messageIndex = 0;
        for (Message message : messageBox) {
            if (messageIDs.get(messageIndex++) == messageID) {
                return message.read();
            }
        }
        return "Message ID does not exist";
    }
}
