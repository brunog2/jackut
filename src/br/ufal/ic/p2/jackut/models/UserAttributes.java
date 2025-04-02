package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa os atributos de um usuário no sistema Jackut.
 */
public class UserAttributes implements Serializable {
    private Map<String, String> attributes;

    /**
     * Construtor da classe UserAttributes.
     * Inicializa o mapa de atributos.
     */
    public UserAttributes() {
        this.attributes = new HashMap<>();
    }

    /**
     * Adiciona um atributo ao usuário.
     *
     * @param name  Nome do atributo.
     * @param value Valor do atributo.
     */
    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    /**
     * Obtém o valor de um atributo do usuário.
     *
     * @param name Nome do atributo.
     * @return Valor do atributo.
     */
    public String getAttribute(String name) {
        return this.attributes.get(name);
    }

    /**
     * Remove um atributo do usuário.
     *
     * @param name Nome do atributo a ser removido.
     */
    public void removeAttribute(String name) {
        this.attributes.remove(name);
    }

    /**
     * Obtém todos os atributos do usuário.
     *
     * @return Mapa contendo todos os atributos do usuário.
     */
    public Map<String, String> getAllAttributes() {
        return new HashMap<>(this.attributes);
    }
}