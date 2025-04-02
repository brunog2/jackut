package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing user sessions in the Jackut system.
 */
public class SessionService {
    private List<String> sessoes;

    /**
     * Constructor for the SessionService class.
     * Initializes the list of sessions.
     */
    public SessionService() {
        this.sessoes = new ArrayList<>();
    }

    /**
     * Adds a session for a user.
     *
     * @param user The user to add a session for.
     */
    public void addSession(User user) {
        sessoes.add(user.getLogin());
    }

    /**
     * Removes all sessions.
     */
    public void removeSessions() {
        sessoes.clear();
    }
}