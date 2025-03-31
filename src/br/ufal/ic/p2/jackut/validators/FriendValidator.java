package br.ufal.ic.p2.jackut.validators;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.services.UserService;

public class FriendValidator {
    private UserService userService;

    public FriendValidator(UserService userService) {
        this.userService = userService;
    }

    public void validateAddFriend(String login, String amigo, User user, User friend) throws UserException {
        if (user == null || friend == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        if (user.getFriendsRequest().contains(friend)) {
            throw new UserException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }

        if (user.getLogin().equals(amigo)) {
            throw new UserException("Usuário não pode adicionar a si mesmo como amigo.");
        }

        if (this.ehAmigo(login, amigo, user, friend)) {
            throw new UserException("Usuário já está adicionado como amigo.");
        }
    }

    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        User currentUser = (user != null) ? user : this.userService.getUser(login);
        User friendUser = (friend != null) ? friend : this.userService.getUser(amigo);

        if (currentUser == null || friendUser == null) {
            return false;
        }

        return currentUser.getFriends().contains(friendUser);
    }
}
