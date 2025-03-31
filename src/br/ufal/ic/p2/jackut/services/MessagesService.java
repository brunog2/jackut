package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;

import java.util.List;

public class MessagesService {
    private UserService userService;

    public MessagesService(UserService userService) {
        this.userService = userService;
    }

    public void enviarRecado(String sessionId, String destinatario, String recado) throws Exception {
        User user = this.userService.getUser(sessionId);
        User receiver = this.userService.getUser(destinatario);

        if (user == null || receiver == null) throw new UserException("Usuário não cadastrado.");

        if (user.getLogin().equals(destinatario)) {
            throw new Exception("Usuário não pode enviar recado para si mesmo.");
        }

        Message message = new Message(recado, user, receiver);
        receiver.getMessages().add(message);
    }

    public String lerRecado(String sessionId) throws Exception {
        User user = this.userService.getUser(sessionId);

        if (user == null) throw new UserException("Usuário não cadastrado.");

        List<Message> messages = user.getMessages();

        if (messages.isEmpty()) {
            throw new Exception("Não há recados.");
        }

        String message = messages.get(0).getContent();

        messages.remove(0);

        return message;
    }
}
