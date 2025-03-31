package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.LoginException;
import br.ufal.ic.p2.jackut.exceptions.UserCreationException;
import br.ufal.ic.p2.jackut.exceptions.UserUpdateException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private List<String> sessoes;
    private final UserRepository userRepository;
    private final UserAttributeManager userAttributeManager;  // A instância do UserAttributeFetcher


    public UserService() {
        this.userRepository = new UserRepository();
        this.userAttributeManager = new UserAttributeManager();
        this.users = userRepository.getUsers();
        this.sessoes = new ArrayList<>();
    }

    public void criarUsuario(String login, String senha, String nome) throws UserCreationException {
        this.validateNewUser(login, senha, nome);

        User user = new User(login, senha, nome);
        users.add(user);
    }

    public String abrirSessao(String login, String senha) throws LoginException {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {

                sessoes.add(user.getLogin());

                return login;
            }
        }
        throw new LoginException("Login ou senha inválidos.");
    }

    public String getAtributoUsuario(String login, String atributo) throws UserCreationException {
        User user = findUser(login);

        if (user == null) throw new UserCreationException("Usuário não cadastrado.");

        String attributeValue = this.userAttributeManager.getAtributo(user, atributo);

        if (attributeValue == null) {
            throw new UserCreationException("Atributo não preenchido.");
        }

        return attributeValue;
    }

    public void editarPerfil(String id, String atributo, String valor) throws UserUpdateException {
        User user = findUser(id);

        if (user == null) {
            throw new UserUpdateException("Usuário não cadastrado.");
        }

        this.userAttributeManager.setAtributo(user, atributo, valor);
    }

    public void zerarSistema() {
        this.users = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    public void encerrarSistema() {
        userRepository.saveUsers(users);
    }

    private void validateNewUser(String login, String senha, String nome) throws UserCreationException {
        User user = findUser(login);

        if (user != null) throw new UserCreationException("Conta com esse nome já existe.");

        if (login == null || login.isEmpty() || login.contains(" ")) {
            throw new UserCreationException("Login inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new UserCreationException("Senha inválida.");
        }
    }

    private User findUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }
}
