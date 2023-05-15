package com.iwona.message;

import java.io.Serializable;

public class Messsage implements Serializable {
    private final int number;
    private final String content;

    public Messsage(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }

}
