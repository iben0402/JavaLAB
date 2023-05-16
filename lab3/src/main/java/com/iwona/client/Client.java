package com.iwona.client;

import com.iwona.message.Messsage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import javax.websocket.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@ClientEndpoint
public class Client extends WebSocketClient {
    boolean serverReadyForMessages = false;

    public Client(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {

        System.out.println("received: " + message);
        if(Objects.equals(message, "ready for messages")) {
            serverReadyForMessages = true;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        Client c = new Client(new URI(
                "ws://localhost:8887"));
        c.connect();

        Scanner scanner = new Scanner(System.in);

        int inputN = scanner.nextInt();
        // Send n to server
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(inputN);
        b.flip();
        byte[] bytes = new byte[4];
        b.get(bytes);
        //byte[] message = b.array();
        c.send(bytes);

        //waiting for server
        while(!c.serverReadyForMessages) {

        }

        // ready for messages
        System.out.print("Enter a message (or 'quit' to exit): ");
        int n = 0;
        String input = "";
        scanner.nextLine();
        while (!input.equals("quit") || n == inputN) {
            System.out.print("Enter a message (or 'quit' to exit): ");
            input = scanner.nextLine();
            System.out.println("You entered: " + input);
            String[] textMessage = input.split(" ");
            Messsage messsage = new Messsage(Integer.parseInt(textMessage[0]), textMessage[1]);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(messsage);
                out.flush();
                byte[] yourBytes = bos.toByteArray();
                c.send(yourBytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
            n++;


        }
        scanner.close();
        System.out.println("Exiting...");

    }
}
