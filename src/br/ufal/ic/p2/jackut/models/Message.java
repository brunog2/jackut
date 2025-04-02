package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;

/**
 * Classe que representa uma mensagem no sistema Jackut.
 */
public class Message implements Serializable {
    private String content;
    private User sender;
    private User receiver;

    /**
     * Construtor da classe Message.
     *
     * @param content  O conteúdo da mensagem.
     * @param sender   O usuário que envia a mensagem.
     * @param receiver O usuário que recebe a mensagem.
     */
    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Obtém o conteúdo da mensagem.
     *
     * @return O conteúdo da mensagem.
     */
    public String getContent() {
        return content;
    }

    /**
     * Obtém o remetente da mensagem.
     *
     * @return O usuário que envia a mensagem.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Obtém o destinatário da mensagem.
     *
     * @return O usuário que recebe a mensagem.
     */
    public User getReceiver() {
        return receiver;
    }
}