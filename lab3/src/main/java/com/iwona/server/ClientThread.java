package com.iwona.server;

import com.iwona.message.Message;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
    protected Socket socket;
    private int id;

    public ClientThread(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());

            output.writeUTF("ready");
            int n = (Integer)input.readObject();
            output.writeUTF("ready for messages");

            for (int i = 0; i < n; i++) {
                Message recievedMessage = (Message) input.readObject();
                System.out.println(id + " sent message: " + recievedMessage.toString());
            }

            output.writeUTF("finished");
            output.close();
            input.close();
            this.socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
