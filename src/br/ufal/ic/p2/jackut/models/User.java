package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    private String login;
    private String senha;
    private String nome;
    private UserAttributes attributes;

    public User(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.attributes = new UserAttributes();
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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Usuário: %s (Login: %s)", this.nome, this.login));
        for (Map.Entry<String, String> entry : this.attributes.getAllAttributes().entrySet()) {
            sb.append(String.format(", %s: %s", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}