package br.ufal.ic.p2.jackut.validators;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import java.util.List;

public class UserValidator {
    public static void validateNewUser(String login, String senha, String nome, List<User> users) throws UserException {
        if (users.stream().anyMatch(user -> user.getLogin().equals(login))) {
            throw new UserException("Conta com esse nome já existe.");
        }
        if (login == null || login.isEmpty() || login.contains(" ")) {
            throw new UserException("Login inválido.");
        }
        if (senha == null || senha.isEmpty()) {
            throw new UserException("Senha inválida.");
        }
    }

    public static User validateLogin(String login, String senha, List<User> users) throws UserException {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new UserException("Login ou senha inválidos."));
    }
}
