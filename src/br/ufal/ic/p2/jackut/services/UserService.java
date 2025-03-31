package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private final UserRepository userRepository;
    private final UserAttributeManager userAttributeManager;
    private final SessionService sessionService;

    public UserService(UserRepository userRepository, UserAttributeManager userAttributeManager, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAttributeManager = userAttributeManager;
        this.sessionService = sessionService;
        this.users = userRepository.getUsers();
    }

    public void criarUsuario(String login, String senha, String nome) throws UserException {
        this.validateNewUser(login, senha, nome);

        User user = new User(login, senha, nome);
        users.add(user);
    }

    public String abrirSessao(String login, String senha) throws UserException {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {

                sessionService.addSession(user);

                return login;
            }
        }
        throw new UserException("Login ou senha inválidos.");
    }

    public String getAtributoUsuario(String login, String atributo) throws UserException {
        User user = getUser(login);

        if (user == null) throw new UserException("Usuário não cadastrado.");

        String attributeValue = this.userAttributeManager.getAtributo(user, atributo);

        if (attributeValue == null) {
            throw new UserException("Atributo não preenchido.");
        }

        return attributeValue;
    }

    public void editarPerfil(String id, String atributo, String valor) throws UserException {
        User user = getUser(id);

        if (user == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        this.userAttributeManager.setAtributo(user, atributo, valor);
    }

    private void validateNewUser(String login, String senha, String nome) throws UserException {
        User user = getUser(login);

        if (user != null) throw new UserException("Conta com esse nome já existe.");

        if (login == null || login.isEmpty() || login.contains(" ")) {
            throw new UserException("Login inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new UserException("Senha inválida.");
        }
    }

    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    public void zerarSistema() {
        this.users = new ArrayList<>();
        this.sessionService.removeSessions();
    }

    public void encerrarSistema() {
        userRepository.saveUsers(users);
    }
}
