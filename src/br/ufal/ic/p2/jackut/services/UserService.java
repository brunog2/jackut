package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.UserRepository;
import br.ufal.ic.p2.jackut.validators.UserValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela gestão de usuários no sistema Jackut.
 */
public class UserService {
    private List<User> users;
    private final UserRepository userRepository;
    private final UserAttributeManager userAttributeManager;
    private final SessionService sessionService;

    /**
     * Construtor do UserService.
     *
     * @param userRepository       Repositório de usuários.
     * @param userAttributeManager Gerenciador de atributos de usuário.
     * @param sessionService       Serviço de gerenciamento de sessões.
     */
    public UserService(UserRepository userRepository, UserAttributeManager userAttributeManager, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAttributeManager = userAttributeManager;
        this.sessionService = sessionService;
        this.users = userRepository.getUsers();
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param login Nome de usuário único.
     * @param senha Senha do usuário.
     * @param nome  Nome real do usuário.
     * @throws UserException Se o login já estiver em uso ou os dados forem inválidos.
     */
    public void criarUsuario(String login, String senha, String nome) throws UserException {
        UserValidator.validateNewUser(login, senha, nome, users);
        User user = new User(login, senha, nome);
        users.add(user);
    }

    /**
     * Abre uma sessão para um usuário autenticado.
     *
     * @param login Nome de usuário.
     * @param senha Senha do usuário.
     * @return O login do usuário autenticado.
     * @throws UserException Se o login ou senha forem inválidos.
     */
    public String abrirSessao(String login, String senha) throws UserException {
        User user = UserValidator.validateLogin(login, senha, users);
        sessionService.addSession(user);
        return login;
    }

    /**
     * Obtém o valor de um atributo do usuário.
     *
     * @param login    Nome de usuário.
     * @param atributo Nome do atributo.
     * @return O valor do atributo.
     * @throws UserException Se o usuário não existir ou o atributo não estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) throws UserException {
        User user = getUser(login);
        if (user == null) throw new UserException("Usuário não cadastrado.");

        String attributeValue = this.userAttributeManager.getAtributo(user, atributo);
        if (attributeValue == null) {
            throw new UserException("Atributo não preenchido.");
        }

        return attributeValue;
    }

    /**
     * Edita um atributo do perfil do usuário.
     *
     * @param id       ID do usuário.
     * @param atributo Nome do atributo a ser editado.
     * @param valor    Novo valor do atributo.
     * @throws UserException Se o usuário não existir.
     */
    public void editarPerfil(String id, String atributo, String valor) throws UserException {
        User user = getUser(id);
        if (user == null) {
            throw new UserException("Usuário não cadastrado.");
        }
        this.userAttributeManager.setAtributo(user, atributo, valor);
    }

    /**
     * Busca um usuário pelo login.
     *
     * @param login Nome de usuário.
     * @return O objeto User se encontrado, ou null caso contrário.
     */
    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Reseta o sistema, removendo todos os usuários e sessões.
     */
    public void zerarSistema() {
        this.users = new ArrayList<>();
        this.sessionService.removeSessions();
    }

    /**
     * Encerra o sistema, salvando os usuários no repositório.
     */
    public void encerrarSistema() {
        userRepository.saveUsers(users);
    }
}
