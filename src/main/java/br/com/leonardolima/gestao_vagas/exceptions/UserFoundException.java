package br.com.leonardolima.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("usuario ja existe");
    }
}
