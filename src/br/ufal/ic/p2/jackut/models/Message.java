package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;

/**
 * Class representing a message in the Jackut system.
 */
public class Message implements Serializable {
    private String content;
    private User sender;
    private User receiver;

    /**
     * Constructor for the Message class.
     *
     * @param content  The content of the message.
     * @param sender   The user who sends the message.
     * @param receiver The user who receives the message.
     */
    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the sender of the message.
     *
     * @return The user who sends the message.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the receiver of the message.
     *
     * @return The user who receives the message.
     */
    public User getReceiver() {
        return receiver;
    }
}