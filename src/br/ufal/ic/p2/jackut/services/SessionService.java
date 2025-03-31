package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private List<String> sessoes;

    public SessionService() {
        this.sessoes = new ArrayList<>();
    }

    public void addSession(User user) {
        sessoes.add(user.getLogin());
    }

    public void removeSessions() {
        sessoes.clear();
    }
}
