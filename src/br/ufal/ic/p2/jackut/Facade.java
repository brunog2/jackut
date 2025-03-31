package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.repositories.UserRepository;
import br.ufal.ic.p2.jackut.services.*;

public class Facade {
    private UserService userService;
    private SessionService sessionService;
    private UserRepository userRepository;
    private UserAttributeManager userAttributeManager;
    private FriendService friendService;
    private MessagesService messagesService;

    public Facade() {
        this.sessionService = new SessionService();
        this.userRepository = new UserRepository();
        this.userAttributeManager = new UserAttributeManager();
        this.userService = new UserService(userRepository, userAttributeManager, sessionService);
        this.friendService = new FriendService(userService);
        this.messagesService = new MessagesService(userService);
    }

    public void criarUsuario(String login, String senha, String nome) throws UserException {
        userService.criarUsuario(login, senha, nome);
    }

    public String abrirSessao(String login, String senha) throws UserException {
        return userService.abrirSessao(login, senha);
    }

    public String getAtributoUsuario(String login, String atributo) throws UserException {
        return userService.getAtributoUsuario(login, atributo);
    }

    public void editarPerfil(String id, String atributo, String valor) throws UserException {
        userService.editarPerfil(id, atributo, valor);
    }

    public boolean ehAmigo(String login, String amigo) {
        return friendService.ehAmigo(login, amigo, null, null);
    }

    public void adicionarAmigo(String login, String amigo) throws UserException {
        friendService.adicionarAmigo(login, amigo);
    }

    public String getAmigos(String login) throws Exception {
        return friendService.getAmigos(login);
    }

    public void zerarSistema() {
        userService.zerarSistema();
    }

    public void encerrarSistema() {
        userService.encerrarSistema();
    }

    public void enviarRecado(String id, String destinatario, String recado) throws Exception {
        messagesService.enviarRecado(id, destinatario, recado);
    }

    public String lerRecado(String id) throws Exception {
        return messagesService.lerRecado(id);
    }
}


