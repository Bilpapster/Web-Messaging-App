package com;

public class Message {
    private boolean isRead;
    private String sender;
    private String receiver;
    private String body;

    public Message(String sender, String receiver, String body) {
        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "(" + sender + ") " + body;
    }

    public String getMessageSignature() {
        StringBuilder output = new StringBuilder();
        output.append("from: ").append(sender);
        if (!this.isRead()) output.append("*");
        return output.toString();
    }

    public String read() {
        this.isRead = true;
        return this.toString();
    }
}
