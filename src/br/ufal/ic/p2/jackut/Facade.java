package br.ufal.ic.p2.jackut;
import br.ufal.ic.p2.jackut.exceptions.LoginException;
import br.ufal.ic.p2.jackut.exceptions.UserCreationException;
import br.ufal.ic.p2.jackut.exceptions.UserUpdateException;
import br.ufal.ic.p2.jackut.services.UserService;

public class Facade {
    private UserService userService;

    public Facade() {
        this.userService = new UserService();
    }

    public void criarUsuario(String login, String senha, String nome) throws UserCreationException {
        userService.criarUsuario(login, senha, nome);
    }

    public String abrirSessao(String login, String senha) throws LoginException {
        return userService.abrirSessao(login, senha);
    }

    public String getAtributoUsuario(String login, String atributo) throws UserCreationException {
        return userService.getAtributoUsuario(login, atributo);
    }


    public void editarPerfil(String id, String atributo, String valor) throws UserUpdateException {
        userService.editarPerfil(id, atributo, valor);
    }

    public void zerarSistema() {
        userService.zerarSistema();
    }

    public void encerrarSistema() {
        userService.encerrarSistema();
    }


}


