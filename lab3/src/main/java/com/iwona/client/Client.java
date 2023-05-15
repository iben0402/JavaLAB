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
import java.util.Map;
import java.util.Scanner;

@ClientEndpoint
public class Client extends WebSocketClient {
    public Client(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public Client(URI serverURI) {
        super(serverURI);
    }

    public Client(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
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
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        Client c = new Client(new URI(
                "ws://localhost:8887")); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("quit")) {
            System.out.print("Enter a command (or 'quit' to exit): ");
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



        }
        scanner.close();
        System.out.println("Exiting...");
    }
}
