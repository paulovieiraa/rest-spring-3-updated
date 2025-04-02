package br.com.pvprojects.restspring3.domain.exception;

public class InternalErrorException extends RuntimeException {

  protected InternalErrorException(String msg) {
    super(msg);
  }

  protected InternalErrorException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
