package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;

public class Message implements Serializable  {
    private String content;
    private User sender;
    private User receiver;

    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
