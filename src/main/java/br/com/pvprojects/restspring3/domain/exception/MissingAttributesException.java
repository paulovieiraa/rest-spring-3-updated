package br.com.pvprojects.restspring3.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissingAttributesException extends BusinessException {

    protected MissingAttributesException(String msg) {
        super(msg);
    }

    public static MissingAttributesException of(String msg) {
        log.warn(msg);
        return new MissingAttributesException(msg);
    }
}