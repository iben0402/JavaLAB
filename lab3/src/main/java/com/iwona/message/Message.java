package com.iwona.message;

import java.io.Serializable;

public class Message implements Serializable {
    private String content;
    private int number;

    public Message(String content, int number) {
        this.content = content;
        this.number = number;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent() {
        this.content = content;
    }

    @Override
    public String toString() {
        return "content: " + this.content + ", number: " + this.number;
    }
}
