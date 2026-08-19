package dev.tsumakov.domain.core.user.exception;

public class UserIdValidationException extends RuntimeException {

  public UserIdValidationException(String message) {
    super(message);
  }
}
