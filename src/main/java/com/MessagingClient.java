package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessagingClient {
    private Account userAccount;

    public static void main(String[] args) throws IOException {




        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while (!(userInput = inputStream.readLine()).equalsIgnoreCase("Bye")) {
            System.out.println("Client: " + userInput);
        }
    }


}