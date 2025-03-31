package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String login;
    private String senha;
    private String nome;
    private UserAttributes attributes;
    private List<User> friends;
    private List<User> friendsRequest;

    public User(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.attributes = new UserAttributes();
        this.friends = new ArrayList<User>();
        this.friendsRequest = new ArrayList<User>();
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getNome() {
        return this.nome;
    }

    public List<User> getFriends() {
        return this.friends;
    }

    public List<User> getFriendsRequest() {
        return this.friendsRequest;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void addAttribute(String name, String value) {
        this.attributes.addAttribute(name, value);
    }


    public String getAttribute(String name) {
        return this.attributes.getAttribute(name);
    }

    @Override
    public String toString() {
      return this.login;
    }
}