package com;

import java.io.Serializable;

public class CommunicationProtocol implements Serializable {
    private static final int CREATE_ACCOUNT = 1;
    private static final int SHOW_ACCOUNTS  = 2;
    private static final int SEND_MESSAGE   = 3;
    private static final int SHOW_INBOX     = 4;
    private static final int READ_MESSAGE   = 5;
    private static final int DELETE_MESSAGE = 6;

    private static final int NUMBER_OF_TOKEN_DIGITS = 6;

    private final MessagingServer parentServer;
    private MessagingClient connectedClient;
    private String errorMessage = null;

    private int functionID;

    public CommunicationProtocol(MessagingServer parentServer) {
        this.parentServer = parentServer;
    }

    public void connectClient(MessagingClient clientToConnect) {
        this.connectedClient = clientToConnect;
    }

    public void freeClient() {
        this.connectedClient = null;
    }

    public String handleClientRequest(String[] args) {
        if (!defineFunctionID(args)) {
            return errorMessage;
        }

        switch (functionID) {
            case CREATE_ACCOUNT:
                return createAccount(args);
            case SHOW_ACCOUNTS:
                return showAccounts(args);
            case SEND_MESSAGE:
                return sendMessage(args);
            case SHOW_INBOX:
                return showInbox(args);
            case READ_MESSAGE:
                return readMessage(args);
            case DELETE_MESSAGE:
                return deleteMessage(args);
            default:
                return "default";
        }
    }

    private boolean defineFunctionID(String[] args) {
//        if (args.length < 3) {
//            errorMessage = "Error in arguments parsing. Not enough parameters to define the function ID.";
//            return false;
//        }
//        try {
//            functionID = Integer.parseInt(args[2]);
//        } catch (NumberFormatException e) {
//            errorMessage = "Error parsing the function ID. The provided argument is not an integer.";
//            return false;
//        }

        if (! areArgumentsEnough(args, 3)) return false;
        if (! isArgumentInteger(args[2])) return false;
        if (functionID < 1 || functionID > 5) {
            errorMessage = "Error parsing the function ID. The provided argument is " +
                    "out of bounds. Accepted values [1, 5]";
            return false;
        }
        return true;
    }

    private String createAccount(String[] args) {
        if (! areArgumentsEnough(args, 4)) return errorMessage;

        String desiredUsername = args[3];

        if (!isUsernameValid(desiredUsername)) return "Invalid username";
        if (!parentServer.isUsernameAvailable(desiredUsername)) return "Sorry, the user already exists";

        int generatedToken;
        synchronized (this) {
            do {
                generatedToken = this.generateRandomToken(NUMBER_OF_TOKEN_DIGITS);
            } while (! parentServer.isTokenAvailable(generatedToken));

            parentServer.createAccount(desiredUsername, generatedToken);
        }
        return Integer.toString(generatedToken);
    }

    private String showAccounts(String[] args) {
        if (! areArgumentsEnough(args, 4)) return errorMessage;
        if (! isArgumentInteger(args[3])) return errorMessage;

        int authenticationToken = Integer.parseInt(args[3]);
        if (! isTokenValid(authenticationToken)) return errorMessage;

        return parentServer.printActiveUsernames();
    }

    private String sendMessage(String[] args) {
        if (! areArgumentsEnough(args, 6)) return errorMessage;
        if (! isArgumentInteger(args[3])) return errorMessage;

        int authenticationToken = Integer.parseInt(args[3]);
        if (! isTokenValid(authenticationToken)) return errorMessage;

        String receiver = args[4];
        if (!parentServer.usernameExists(receiver)) return (errorMessage = "User does not exist");

        String messageBody = args[5];
        parentServer.addMessage(authenticationToken, receiver, messageBody);
            return "OK";
    }

    private String showInbox(String[] args) {
        if (! areArgumentsEnough(args, 4)) return errorMessage;
        if (! isArgumentInteger(args[3])) return errorMessage;

        int authenticationToken = Integer.parseInt(args[3]);
        if (! isTokenValid(authenticationToken)) return errorMessage;

        return parentServer.printInboxMessages(authenticationToken);
    }

    private String readMessage(String[] args) {
        return "read message";
    }

    private String deleteMessage(String[] args) {
        return "delete message";
    }

    private boolean isTokenValid(int authenticationToken) {
        if (parentServer.isTokenValid(authenticationToken)) return true;

        errorMessage = "Invalid Auth Token";
        return false;
    }

    private int generateRandomToken(int numberOfDigits) {
        int generatedToken = RandomEngine.getInstance().nextInt(10);
        for (int digit = 2; digit <= numberOfDigits; digit++) {
            generatedToken += RandomEngine.getInstance().nextInt() * 10 ^ (digit - 1);
        }
        return generatedToken;
    }

    private boolean isUsernameValid(String username) {
        for (int charIndex = 0; charIndex <= username.length(); charIndex++) {
            if (! (isCharAlphanumeric(username.charAt(charIndex)) ||
                    isCharUnderscore(username.charAt(charIndex)))) return false;
        }
        return true;
    }

    private boolean isCharAlphanumeric(char characterToCheck) {
        return (characterToCheck >= 'A' && characterToCheck <= 'Z') ||
                (characterToCheck >= 'a' && characterToCheck <= 'z') ||
                (characterToCheck >= '1' && characterToCheck <= '9');
    }

    private boolean isCharUnderscore(char characterToCheck) {
        return (characterToCheck =='_');
    }

    private boolean areArgumentsEnough(String[] args, int minimumLength) {
        if (args.length >= minimumLength) return true;

        errorMessage = "Not enough arguments. Check the function manual and try again";
        return false;
    }

    private boolean isArgumentInteger(String argument) {
        try {
            Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            errorMessage = "Parsing error. One or more provided argument(s) are not integer.";
            return false;
        }
    }
}
