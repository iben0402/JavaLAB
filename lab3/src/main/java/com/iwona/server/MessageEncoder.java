package com.iwona.server;

import com.iwona.message.Messsage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class MessageEncoder implements Runnable {
    private final byte[] message;

    public MessageEncoder(byte[] message) {
        this.message = message;
    }


    @Override
    public void run() {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Messsage o = (Messsage) in.readObject();
            System.out.println("Recieved: " + o.getNumber() + " " + o.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
