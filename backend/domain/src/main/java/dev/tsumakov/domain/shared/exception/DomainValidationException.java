package dev.tsumakov.domain.shared.exception;

public class DomainValidationException extends RuntimeException {

  public DomainValidationException(String message) {
    super(message);
  }
}
