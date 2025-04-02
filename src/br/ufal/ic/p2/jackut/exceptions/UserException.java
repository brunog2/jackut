package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção personalizada para erros relacionados a usuários no sistema Jackut.
 */
public class UserException extends Exception {

    /**
     * Construtor da exceção UserException.
     *
     * @param message Mensagem de erro detalhada.
     */
    public UserException(String message) {
        super(message);
    }
}