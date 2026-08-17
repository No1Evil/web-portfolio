package dev.tsumakov.application.core.user.exception;

public class PasswordValidationException extends RuntimeException {

  public PasswordValidationException(String message) {
    super(message);
  }
}
