package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Class responsible for managing user attributes in the Jackut system.
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
     * Gets the value of a specified attribute for a user.
     *
     * @param user     The user whose attribute is to be retrieved.
     * @param atributo The name of the attribute.
     * @return The value of the attribute.
     */
    public String getAtributo(User user, String atributo) {
        Function<User, String> getter = ATTRIBUTE_GETTERS.get(atributo);
        if (getter != null) {
            return getter.apply(user);
        }
        return user.getAttribute(atributo);
    }

    /**
     * Sets the value of a specified attribute for a user.
     *
     * @param user     The user whose attribute is to be set.
     * @param atributo The name of the attribute.
     * @param valor    The value to set for the attribute.
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