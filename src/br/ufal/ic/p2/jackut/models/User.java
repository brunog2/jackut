package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um usuário no sistema Jackut.
 */
public class User implements Serializable {
    private String login;
    private String senha;
    private String nome;
    private UserAttributes attributes;
    private List<User> friends;
    private List<User> friendsRequest;
    private List<Message> messages;

    /**
     * Construtor da classe User.
     *
     * @param login Nome de usuário.
     * @param senha Senha do usuário.
     * @param nome  Nome real do usuário.
     */
    public User(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.attributes = new UserAttributes();
        this.friends = new ArrayList<User>();
        this.friendsRequest = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
    }

    /**
     * Obtém o login do usuário.
     *
     * @return Login do usuário.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return Senha do usuário.
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Obtém o nome real do usuário.
     *
     * @return Nome real do usuário.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém a lista de amigos do usuário.
     *
     * @return Lista de amigos.
     */
    public List<User> getFriends() {
        return this.friends;
    }

    /**
     * Obtém a lista de solicitações de amizade do usuário.
     *
     * @return Lista de solicitações de amizade.
     */
    public List<User> getFriendsRequest() {
        return this.friendsRequest;
    }

    /**
     * Obtém a lista de mensagens do usuário.
     *
     * @return Lista de mensagens.
     */
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * Define o login do usuário.
     *
     * @param login Novo login do usuário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define o nome real do usuário.
     *
     * @param nome Novo nome real do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha Nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Adiciona um atributo ao usuário.
     *
     * @param name  Nome do atributo.
     * @param value Valor do atributo.
     */
    public void addAttribute(String name, String value) {
        this.attributes.addAttribute(name, value);
    }

    /**
     * Obtém o valor de um atributo do usuário.
     *
     * @param name Nome do atributo.
     * @return Valor do atributo.
     */
    public String getAttribute(String name) {
        return this.attributes.getAttribute(name);
    }

    /**
     * Retorna a representação em string do usuário.
     *
     * @return Login do usuário.
     */
    @Override
    public String toString() {
        return this.login;
    }
}