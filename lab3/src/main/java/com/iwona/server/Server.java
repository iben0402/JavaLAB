package com.iwona.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static int port = 8080;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        int idx = 0;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while(true) {

                try {
                    Socket socket = server.accept();
                    System.out.println("Connected id: " + idx);
                    ClientThread clientThread = new ClientThread(socket, idx);
                    idx++;
                    Thread th = new Thread(clientThread);
                    th.start();
                }
                catch (IOException ex2) {
                    throw new RuntimeException(ex2);
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
