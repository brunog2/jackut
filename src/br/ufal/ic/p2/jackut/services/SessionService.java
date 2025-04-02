package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por gerenciar sessões de usuários no sistema Jackut.
 */
public class SessionService {
    private List<String> sessoes;

    /**
     * Construtor da classe SessionService.
     * Inicializa a lista de sessões.
     */
    public SessionService() {
        this.sessoes = new ArrayList<>();
    }

    /**
     * Adiciona uma sessão para um usuário.
     *
     * @param user O usuário para adicionar uma sessão.
     */
    public void addSession(User user) {
        sessoes.add(user.getLogin());
    }

    /**
     * Remove todas as sessões.
     */
    public void removeSessions() {
        sessoes.clear();
    }
}