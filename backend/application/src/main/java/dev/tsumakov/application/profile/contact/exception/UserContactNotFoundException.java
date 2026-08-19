package dev.tsumakov.application.profile.contact.exception;

public class UserContactNotFoundException extends RuntimeException {

  public UserContactNotFoundException(String message) {
    super(message);
  }
}
