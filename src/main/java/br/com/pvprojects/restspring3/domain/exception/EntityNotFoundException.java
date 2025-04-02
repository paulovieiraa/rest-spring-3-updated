package br.com.pvprojects.restspring3.domain.exception;

public class EntityNotFoundException extends BusinessException {

    protected EntityNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    protected EntityNotFoundException(String msg) {
        super(msg);
    }

    public static EntityNotFoundException of(String msg, Throwable cause) {
        return new EntityNotFoundException(msg, cause);
    }
}
