package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;

import java.util.List;

/**
 * Serviço responsável pelo gerenciamento de mensagens no sistema Jackut.
 */
public class MessagesService {
    private UserService userService;

    /**
     * Construtor do MessagesService.
     *
     * @param userService Serviço de usuários.
     */
    public MessagesService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Envia um recado de um usuário para outro.
     *
     * @param sessionId   ID da sessão do usuário remetente.
     * @param destinatario Login do usuário destinatário.
     * @param recado      Conteúdo do recado.
     * @throws Exception Se o usuário não estiver cadastrado ou tentar enviar recado para si mesmo.
     */
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

    /**
     * Lê o próximo recado da lista de recados de um usuário.
     *
     * @param sessionId ID da sessão do usuário.
     * @return O conteúdo do recado.
     * @throws Exception Se o usuário não estiver cadastrado ou não houver recados.
     */
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