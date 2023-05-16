package com.iwona.client;

import com.iwona.message.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected to the server on port 8080");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String line = input.readUTF();
            System.out.println("Recieved from server: " + line);
            System.out.println("Write n (number of messages): ");
            int n = scanner.nextInt();
            output.writeObject(n);

            line = input.readUTF();
            System.out.println("Recieved from server: " + line);
            System.out.println("Write n messages (number + message) \n");

            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                String inputMessage = scanner.nextLine();
                String msg[] = inputMessage.split(" ");
                Message message = new Message(msg[1], Integer.parseInt(msg[0]));
                output.writeObject(message);
            }

            line=input.readUTF();
            System.out.println("Recieved from server: " + line);
            input.close();
            output.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
