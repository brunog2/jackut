package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Classe responsável por gerenciar atributos de usuários no sistema Jackut.
 */
public class UserAttributeManager {
    private static final Map<String, Function<User, String>> ATTRIBUTE_GETTERS = new HashMap<>();
    private static final Map<String, BiConsumer<User, String>> ATTRIBUTE_SETTERS = new HashMap<>();

    static {
        ATTRIBUTE_GETTERS.put("login", User::getLogin);
        ATTRIBUTE_GETTERS.put("nome", User::getNome);
        ATTRIBUTE_GETTERS.put("senha", User::getSenha);

        ATTRIBUTE_SETTERS.put("login", User::setLogin);
        ATTRIBUTE_SETTERS.put("senha", User::setSenha);
        ATTRIBUTE_SETTERS.put("nome", User::setNome);
    }

    /**
     * Obtém o valor de um atributo especificado para um usuário.
     *
     * @param user     O usuário cujo atributo deve ser recuperado.
     * @param atributo O nome do atributo.
     * @return O valor do atributo.
     */
    public String getAtributo(User user, String atributo) {
        Function<User, String> getter = ATTRIBUTE_GETTERS.get(atributo);
        if (getter != null) {
            return getter.apply(user);
        }
        return user.getAttribute(atributo);
    }

    /**
     * Define o valor de um atributo especificado para um usuário.
     *
     * @param user     O usuário cujo atributo deve ser definido.
     * @param atributo O nome do atributo.
     * @param valor    O valor a ser definido para o atributo.
     */
    public void setAtributo(User user, String atributo, String valor) {
        BiConsumer<User, String> setter = ATTRIBUTE_SETTERS.get(atributo);
        if (setter != null) {
            setter.accept(user, valor);
        } else {
            user.addAttribute(atributo, valor);
        }
    }
}