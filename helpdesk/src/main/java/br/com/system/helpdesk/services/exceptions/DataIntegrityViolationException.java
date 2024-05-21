package br.com.system.helpdesk.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
