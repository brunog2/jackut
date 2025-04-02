package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.repositories.UserRepository;
import br.ufal.ic.p2.jackut.services.*;
import br.ufal.ic.p2.jackut.validators.UserValidator;

/**
 * Facade para o sistema Jackut, responsável por gerenciar a interação entre os serviços.
 */
public class Facade {
    private UserService userService;
    private SessionService sessionService;
    private UserRepository userRepository;
    private UserAttributeManager userAttributeManager;
    private FriendService friendService;
    private MessagesService messagesService;

    /**
     * Construtor da Facade, inicializa todos os serviços necessários.
     */
    public Facade() {
        this.sessionService = new SessionService();
        this.userRepository = new UserRepository();
        this.userAttributeManager = new UserAttributeManager();
        this.userService = new UserService(userRepository, userAttributeManager, sessionService);
        this.friendService = new FriendService(userService);
        this.messagesService = new MessagesService(userService);
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
        userService.criarUsuario(login, senha, nome);
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
        return userService.abrirSessao(login, senha);
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
        return userService.getAtributoUsuario(login, atributo);
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
        userService.editarPerfil(id, atributo, valor);
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @return true se forem amigos, false caso contrário.
     */
    public boolean ehAmigo(String login, String amigo) {
        return friendService.ehAmigo(login, amigo, null, null);
    }

    /**
     * Adiciona um amigo para um usuário.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @throws UserException Se houver algum problema ao adicionar o amigo.
     */
    public void adicionarAmigo(String login, String amigo) throws UserException {
        friendService.adicionarAmigo(login, amigo);
    }

    /**
     * Obtém a lista de amigos de um usuário.
     *
     * @param login Nome de usuário.
     * @return Lista de amigos.
     * @throws Exception Se houver algum problema ao obter a lista de amigos.
     */
    public String getAmigos(String login) throws Exception {
        return friendService.getAmigos(login);
    }

    /**
     * Reseta o sistema, removendo todos os usuários e sessões.
     */
    public void zerarSistema() {
        userService.zerarSistema();
    }

    /**
     * Encerra o sistema, salvando os usuários no repositório.
     */
    public void encerrarSistema() {
        userService.encerrarSistema();
    }

    /**
     * Envia um recado de um usuário para outro.
     *
     * @param id          ID da sessão do usuário remetente.
     * @param destinatario Login do usuário destinatário.
     * @param recado      Conteúdo do recado.
     * @throws Exception Se houver algum problema ao enviar o recado.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception {
        messagesService.enviarRecado(id, destinatario, recado);
    }

    /**
     * Lê o próximo recado da lista de recados de um usuário.
     *
     * @param id ID da sessão do usuário.
     * @return O conteúdo do recado.
     * @throws Exception Se houver algum problema ao ler o recado.
     */
    public String lerRecado(String id) throws Exception {
        return messagesService.lerRecado(id);
    }
}