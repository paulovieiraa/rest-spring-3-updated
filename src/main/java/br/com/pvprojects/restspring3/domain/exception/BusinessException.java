package br.com.pvprojects.restspring3.domain.exception;

public class BusinessException extends RuntimeException {
    protected BusinessException(String msg) {
        super(msg);
    }

    protected BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public static BusinessException of(String msg, Throwable cause) {
        return new BusinessException(msg, cause);
    }

    public static BusinessException of(String msg) {
        return new BusinessException(msg);
    }
}