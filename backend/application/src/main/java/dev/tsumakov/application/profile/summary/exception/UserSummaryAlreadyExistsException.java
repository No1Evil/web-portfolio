package dev.tsumakov.application.profile.summary.exception;

public class UserSummaryAlreadyExistsException extends RuntimeException {

  public UserSummaryAlreadyExistsException(String message) {
    super(message);
  }
}