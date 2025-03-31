package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.validators.FriendValidator;

public class FriendService {
    private UserService userService;
    private FriendValidator friendValidator;

    public FriendService(UserService userService) {
        this.userService = userService;
        this.friendValidator = new FriendValidator(userService);
    }

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

    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        return this.friendValidator.ehAmigo(login, amigo, user, friend);
    }

}
