package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.validators.FriendValidator;

/**
 * Serviço responsável por gerenciar as operações relacionadas a amigos no sistema Jackut.
 */
public class FriendService {
    private UserService userService;
    private FriendValidator friendValidator;

    /**
     * Construtor do FriendService.
     *
     * @param userService Serviço de usuários.
     */
    public FriendService(UserService userService) {
        this.userService = userService;
        this.friendValidator = new FriendValidator(userService);
    }

    /**
     * Adiciona um amigo para um usuário.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @throws UserException Se houver algum problema ao adicionar o amigo.
     */
    public void adicionarAmigo(String login, String amigo) throws UserException {
        User user = this.userService.getUser(login);
        User friend = this.userService.getUser(amigo);

        this.friendValidator.validateAddFriend(login, amigo, user, friend);

        user.getFriendsRequest().add(friend);

        if (friend.getFriendsRequest().contains(user)) {
            user.getFriendsRequest().remove(friend);
            friend.getFriendsRequest().remove(user);
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
    }

    /**
     * Obtém a lista de amigos de um usuário.
     *
     * @param login Nome de usuário.
     * @return Lista de amigos.
     * @throws Exception Se houver algum problema ao obter a lista de amigos.
     */
    public String getAmigos(String login) throws Exception {
        User user = this.userService.getUser(login);

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

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @param user  Usuário remetente.
     * @param friend Usuário destinatário.
     * @return true se forem amigos, false caso contrário.
     */
    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        return this.friendValidator.ehAmigo(login, amigo, user, friend);
    }
}