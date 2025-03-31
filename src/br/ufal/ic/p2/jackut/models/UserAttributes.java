package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import java.io.Serializable;

public class UserAttributes implements Serializable {
    private Map<String, String> attributes;

    public UserAttributes() {
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public String getAttribute(String name) {
        return this.attributes.get(name);
    }

    public void removeAttribute(String name) {
        this.attributes.remove(name);
    }

    public Map<String, String> getAllAttributes() {
        return new HashMap<>(this.attributes);
    }
}


