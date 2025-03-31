package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private final UserRepository userRepository;
    private final UserAttributeManager userAttributeManager;  // A instância do UserAttributeFetcher
    private final SessionService sessionService;

    public UserService() {
        this.userRepository = new UserRepository();
        this.userAttributeManager = new UserAttributeManager();
        this.sessionService = new SessionService();
        this.users = userRepository.getUsers();
    }

    public void criarUsuario(String login, String senha, String nome) throws UserCreationException {
        this.validateNewUser(login, senha, nome);

        User user = new User(login, senha, nome);
        users.add(user);
    }

    public String abrirSessao(String login, String senha) throws LoginException {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {

                sessionService.addSession(user);

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

    public void adicionarAmigo(String login, String amigo) throws AddFriendException {
        User user = findUser(login);
        User friend = findUser(amigo);

        this.validateAddFriend(login, amigo, user, friend);

        user.getFriendsRequest().add(friend);

        if (friend.getFriendsRequest().contains(user)) {
            user.getFriendsRequest().remove(friend);
            friend.getFriendsRequest().remove(user);
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
    }


    public void zerarSistema() {
        this.users = new ArrayList<>();
        this.sessionService.removeSessions();
    }

    public void encerrarSistema() {
        userRepository.saveUsers(users);
    }

    private void validateAddFriend(String login, String amigo, User user, User friend) throws AddFriendException {
        if (user == null || friend == null) {
            throw new AddFriendException("Usuário não cadastrado.");
        }

        if (user.getFriendsRequest().contains(friend)) {
            throw new AddFriendException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }

        if (user.getLogin().equals(amigo)) {
            throw new AddFriendException("Usuário não pode adicionar a si mesmo como amigo.");
        }

        if (this.ehAmigo(login, amigo, user, friend)) {
            throw new AddFriendException("Usuário já está adicionado como amigo.");
        }
    }

    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        User currentUser = (user != null) ? user : findUser(login);
        User friendUser = (friend != null) ? friend : findUser(amigo);

        if (currentUser == null || friendUser == null) {
            return false;
        }

        return currentUser.getFriends().contains(friendUser);
    }

    public String getAmigos(String login) throws Exception {
        User user = this.findUser(login);

        if (user == null) throw new Exception("Usuário não encontrado.");

        String friends = String.join(
                ",",
                user
                        .getFriends()
                        .stream()
                        .map(User::getLogin)
                        .toArray(String[]::new)
        );

        return "{" + friends + "}";
    }

    public void enviarRecado(String sessionId, String destinatario, String recado) throws Exception {
        User user = findUser(sessionId);
        User receiver = findUser(destinatario);

        if (user == null || receiver == null) throw new UserNotFoundException("Usuário não cadastrado.");

        if (user.getLogin().equals(destinatario)) {
            throw new Exception("Usuário não pode enviar recado para si mesmo.");
        }

        Message message = new Message(recado, user, receiver);
        receiver.getMessages().add(message);
    }

    public String lerRecado(String sessionId) throws Exception {
        User user = findUser(sessionId);

        if (user == null) throw new UserNotFoundException("Usuário não cadastrado.");

        List<Message> messages = user.getMessages();

        if (messages.isEmpty()) {
            throw new Exception("Não há recados.");
        }

        String message = messages.get(0).getContent();

        messages.remove(0);

        return message;
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
